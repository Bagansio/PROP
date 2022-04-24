package com.recommender.recommenderapp.Domain.Models;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Represents the Content Based Filtering Algorithm.
 * @version 1.0, 20/11/21
 * @author Alex
 */
public class ContentBasedFiltering extends Algorithm{

    private Integer limit = 3;

    public void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap) {

    }


    /**
     *
     * @param value -> result previous normalized
     * @param min -> min value used to normalize
     * @param max -> max value used to normalize
     * @return value normalized
     */
    private Double normalizeValue(Double value, Double min, Double max){
        // if max != min we normalize value
        if(value > max)
            return 1.0;

        if(value < min)
            return 0.0;

        if(max > min) {
            return ((value - min) / (max - min));
        }
        return max; //min == max we return max because all the attributes of this type are equal
    }

    /**
     *
     * @param  items Map - key (ItemId) value(item itself)
     * @return  A map with key (attributeName) and value (an Array with two values [0] min Value of this attribute and
     *          [1] max value of this attribute.
     */
    private Map<String,Double[]> findMinMaxOfItems(Map<String,Item> items){
        Map<String,Double[]> minMax = new HashMap<>();
        for(Item item : items.values()){
            Map<String, Integer> intAttributes = item.getIntAttributes();
            for(String attributeName : intAttributes.keySet()){
                Double value = intAttributes.get(attributeName).doubleValue();
                if(minMax.containsKey(attributeName)){
                    Double[] currentMinMax = minMax.get(attributeName);
                    if(currentMinMax[0] > value){
                        currentMinMax[0] = value;
                    }
                    if(currentMinMax[1] < value){
                        currentMinMax[1] = value;
                    }
                }
                else{
                    Double[] aux = {value,value};
                    minMax.put(attributeName, aux);
                }
            }
            Map<String,Double> doubleAttributes = item.getDoubleAttributes();
            for(String attributeName : doubleAttributes.keySet()){
                Double value = doubleAttributes.get(attributeName);
                if(minMax.containsKey(attributeName)){
                    Double[] currentMinMax = minMax.get(attributeName);
                    if(currentMinMax[0] > value){
                        currentMinMax[0] = value;
                    }
                    if(currentMinMax[1] < value){
                        currentMinMax[1] = value;
                    }
                }
                else{
                    Double[] aux = {value,value};
                    minMax.put(attributeName, aux);
                }
            }
        }
        return minMax;
    }


    /**
     * Giving a User user filter the items using the average of the item rates.
     * @param  user User that contains knownItems (items / itemList)
     * @return Map key (itemId) and value an Item that have biggest rating than average.
     */
    private Map<String,Item> filterKnownItems(User user){
        Double average = 0.0;
        Map<String,Double> knownRates = user.getRatings();
        Map<String,Item> filteredKnownItems = new HashMap<>();
        for(Double rate : knownRates.values()){
            average += rate;
        }
        average /= knownRates.size();
        for(String itemId : knownRates.keySet()){
            if(average <= knownRates.get(itemId)){
                filteredKnownItems.put(itemId, user.getItem(itemId));
            }
        }
        return filteredKnownItems;
    }

    /**
     * Giving a neighbours this function sort by value (distance) and then take the "limit" best
     * @param neighbours  Map - key (itemUnknownId) value (distance between itemUnknown and an itemKnown)
     * @return            Sorted Map key (itemId) and value (Distance) with max size = limit(k).
     */
    private Map<String,Double> filterNearestNeighbours(Map<String,Double> neighbours){
        Map<String,Double> nearestNeighbours = new LinkedHashMap<>();

        neighbours = neighbours.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Iterator<Map.Entry<String,Double>> it = neighbours.entrySet().iterator();
        int i = 0;
        while(i < limit && it.hasNext()){
            Map.Entry<String,Double> entry = it.next();
            nearestNeighbours.put(entry.getKey(),entry.getValue());
            ++i;
        }
        return nearestNeighbours;
    }

    /**
     *
     * @param filteredKnownItems  Map with key (itemKnownId) and value (the item itself)
     * @param unknownItems        Map with key (itemUnknownId) and value (the item itself)
     * @return                    Map {key->ItemKnwonId}, {value -> Map (key -> itemUnknownId) (value -> Distance
     *                            between itemKnown and itemUnknown}  This has size = limit
     */
    private Map<String,Map<String,Double>> getNeighbourhood(Map<String,Item> filteredKnownItems,Map<String,Item> unknownItems){
        Map<String,Double[]> attributesMinMax = findMinMaxOfItems(filteredKnownItems);
        Map<String,Map<String,Double>> neighbours = new HashMap<>();
        for(String itemId : filteredKnownItems.keySet()){
            Item knownItem = filteredKnownItems.get(itemId);
            Map<String,Double> nearestNeighbours = filterNearestNeighbours(getDistances(knownItem,unknownItems, attributesMinMax));
            neighbours.put(itemId,nearestNeighbours);
        }
        return neighbours;
    }

    /**
     *
     * @param knownItemAttributes    Map - key(attributeName) value(attributeValue)
     * @param unknownItemAttributes  Map - key(attributeName) value(attributeValue)
     * @return                       The distance between the String Attributes of knownItem and unknownItem
     */
    private Double distanceStringAttributes(Map<String,String> knownItemAttributes, Map<String,String> unknownItemAttributes){
        Double distance = 0.0;
        for(String attributeName : unknownItemAttributes.keySet()){
            if(! knownItemAttributes.containsKey(attributeName) || knownItemAttributes.get(attributeName) != unknownItemAttributes.get(attributeName)){
                ++distance;
            }
        }
        return Math.pow(distance,2);
    }


    /**
     *
     * @param knownItemAttributes   Map - key(attributeName) value(attributeValue)
     * @param unknownItemAttributes Map - key(attributeName) value(attributeValue)
     * @param attributesMinMax      Map - key(attributeName) value(an Array with two values [0] min Value of this
     *                              attribute and [1] max Value of this attribute)
     * @return                      The normalized distance between the Int Attributes of knownItem and unknownItem
     */
    private Double distanceIntAttributes(Map<String,Integer> knownItemAttributes, Map<String,Integer> unknownItemAttributes, Map<String,Double[]> attributesMinMax){
        Double distance = 0.0;
        for(String attributeName : unknownItemAttributes.keySet()){
            if(knownItemAttributes.containsKey(attributeName)){
                Double[] minMaxOfAttribute = attributesMinMax.get(attributeName);
                Double unknownNormalized = normalizeValue(unknownItemAttributes.get(attributeName).doubleValue(),minMaxOfAttribute[0],minMaxOfAttribute[1]);
                Double knownNormalized = normalizeValue(knownItemAttributes.get(attributeName).doubleValue(),minMaxOfAttribute[0],minMaxOfAttribute[1]);

                distance += Math.abs(knownNormalized - unknownNormalized);
            }
        }
        return Math.pow(distance,2);
    }

    /**
     *
     * @param knownItemAttributes    Map - key(attributeName) value(attributeValue)
     * @param unknownItemAttributes  Map - key(attributeName) value(attributeValue)
     * @param attributesMinMax       Map - key(attributeName) value(an Array with two values [0] min Value of this
     *                               attribute and [1] max Value of this attribute)
     * @return                       The normalized distance between the Int Attributes of knownItem and unknownItem
     */
    private Double distanceDoubleAttributes(Map<String,Double> knownItemAttributes, Map<String,Double> unknownItemAttributes, Map<String,Double[]> attributesMinMax){
        Double distance = 0.0;
        for(String attributeName : unknownItemAttributes.keySet()){
            if(knownItemAttributes.containsKey(attributeName)){
                Double[] minMaxOfAttribute = attributesMinMax.get(attributeName);
                Double unknownNormalized = normalizeValue(unknownItemAttributes.get(attributeName),minMaxOfAttribute[0],minMaxOfAttribute[1]);
                Double knownNormalized = normalizeValue(knownItemAttributes.get(attributeName),minMaxOfAttribute[0],minMaxOfAttribute[1]);

                distance += Math.abs(knownNormalized - unknownNormalized);
            }
        }
        return Math.pow(distance,2);
    }

    /**
     * 
     * @param knownItemAttributes     Map - key(attributeName) value(attributeValue)
     * @param unknownItemAttributes   Map - key(attributeName) value(attributeValue)
     * @return                        Disctance between the Set Attributes of knownItem and unknown Item
     *                                (using Intersection)
     */
    private Double distanceSetAttributes(Map<String, Set<String>> knownItemAttributes, Map<String,Set<String>> unknownItemAttributes){
        Double distance = 0.0;
        for(String attributeName : unknownItemAttributes.keySet()){
            if(knownItemAttributes.containsKey(attributeName)) {
                Set<String> intersection = new HashSet<>(unknownItemAttributes.get(attributeName));
                intersection.retainAll(knownItemAttributes.get(attributeName));
                Integer originalSize = unknownItemAttributes.get(attributeName).size();

                if(originalSize != 0)  distance += ((originalSize - intersection.size()) / originalSize.doubleValue());
            }
        }
        return Math.pow(distance,2);
    }

    /**
     *                          Distances between a knownItem and a Set(Map) of UnknownItems
     *                          
     * @param knownItem         an Item
     * @param unknownItems      A map - key (unknowItemId) and value the item itself
     * @param attributesMinMax  A Map - key(attributeName) value ([0] minAttributeValue, [1] maxAttributeValue)
     * @return                  Map - key (itemUnknownId) value(Distance between itemUnknown and itemKnown)
     */
    private Map<String,Double> getDistances(Item knownItem, Map<String,Item> unknownItems, Map<String,Double[]> attributesMinMax){
        Map<String,Double> distances = new HashMap<>();
        for(Item unknownItem : unknownItems.values()){

            Double currentDistance = distanceStringAttributes(knownItem.getStringAttributes(),unknownItem.getStringAttributes());
            currentDistance += distanceSetAttributes(knownItem.getSetAttributes(),unknownItem.getSetAttributes());
            currentDistance += distanceIntAttributes(knownItem.getIntAttributes(),unknownItem.getIntAttributes(),attributesMinMax);
            currentDistance += distanceDoubleAttributes(knownItem.getDoubleAttributes(),unknownItem.getDoubleAttributes(),attributesMinMax);
            distances.put(unknownItem.getId(),Math.sqrt(currentDistance));
        }

        return distances;
    }

    /**
     *                          Given a neighbourhood and a knownItemRating return the items with best calculated rating.
     * @param neighbourhood     A map with key (itemKnownId), and Value ( Map with key (itemUnknownId) and value
     *                          (Distance between itemKnown and itemUnknown))
     * @param knownItemsRating  Rates of the items known | Map - key (itemKnownId) value(rate)
     * @return                  the items with best calculated rating
     */
    public Map<String,Double> filterNeighbourhood(Map<String,Map<String,Double>> neighbourhood, Map<String,Double> knownItemsRating){
        Map<String,Double[]> mostSimilarItems = new HashMap<>();
        Map<String,Integer> occurrences = new HashMap<>();
        Double maxDistance = -1.0;
        Double minDistance = Double.MAX_VALUE;

        for(String itemKnownId : neighbourhood.keySet()){
            for(Double distance : neighbourhood.get(itemKnownId).values()){
                if(distance > maxDistance)
                    maxDistance = distance;
                if(distance < minDistance)
                    minDistance = distance;
            }
        }

        for(String itemKnownId : neighbourhood.keySet()){
            Double itemRate = knownItemsRating.get(itemKnownId);
            for(String itemUnknownId : neighbourhood.get(itemKnownId).keySet()){
                Double specifiedRate = itemRate * (1-normalizeValue(neighbourhood.get(itemKnownId).get(itemUnknownId),minDistance,maxDistance));
                if(mostSimilarItems.containsKey(itemUnknownId)){
                    Double[] sumDistances = mostSimilarItems.get(itemUnknownId);
                    sumDistances[0] = specifiedRate + sumDistances[0];
                    sumDistances[1] = itemRate + (itemRate - specifiedRate) + sumDistances[1];
                    mostSimilarItems.put(itemUnknownId,sumDistances);
                    occurrences.put(itemUnknownId,occurrences.get(itemUnknownId) + 1);
                }
                else{
                    Double[] aux = {specifiedRate,itemRate + (itemRate - specifiedRate)};
                    mostSimilarItems.put(itemUnknownId, aux);
                    occurrences.put(itemUnknownId,1);
                }
            }
        }
        Map<String,Double> result = new HashMap<>();
        for(String itemUnknownId : mostSimilarItems.keySet()){
            Integer occurrence = occurrences.get(itemUnknownId);
            Double[] sumRates = mostSimilarItems.get(itemUnknownId);
            result.put(itemUnknownId,((sumRates[0]+sumRates[1])/2.0)/occurrence);
        }

        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * Gived an User and their unknownItems returns the best unknownItems based on their knownItems
     * @param user A user with known items
     * @param unknownItems Map - key (unknownItemId) and value (item itself)
     * @param Q  number of items that must be recommended
     * @return Ordered Map with key (itemUnknowItemId) and value (expected rate)
     */
    public Map<String,Double> query(User user, Map<String, Item> unknownItems, int Q){
        limit = Q;
        Map<String,Item> filteredKnownItems = filterKnownItems(user);
        Map<String,Map<String,Double>> neighbourhood = getNeighbourhood(filteredKnownItems,unknownItems);
        return filterNeighbourhood(neighbourhood,user.getRatings());
    }
}
