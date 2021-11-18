package com.recommender.recommenderapp.Domain.Models;

import java.util.*;
import java.util.stream.Collectors;

public class ContentBasedFiltering extends Algorithm{

    private Integer limit;
    @Override
    public void preprocessingData() {

    }

    @Override
    public void executeAlgorithm() {

    }


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

    private Map<String,Double> filterNearestNeighbours(Map<String,Double> neighbours){
        Map<String,Double> nearestNeighbours = new LinkedHashMap<>();
        /*
        System.out.println("-------------------------");

        neighbours.forEach((k,v)-> {
            System.out.println(k +": " + v);
        });
        */
        neighbours = neighbours.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Iterator<Map.Entry<String,Double>> it = neighbours.entrySet().iterator();
        int i = 0;
        while(i < limit && it.hasNext()){
            Map.Entry<String,Double> entry = it.next();
            nearestNeighbours.put(entry.getKey(),entry.getValue());
            ++i;
        }
        /*
        System.out.println("++++++++++++++++++++++");
        nearestNeighbours.forEach((k,v) -> {
            System.out.println(k + ": " + v);
        });

        System.out.println("////////////////////");

        */
        return nearestNeighbours;
    }

    /**
     *
     * @param filteredKnownItems
     * @param unknownItems
     * @return Map {key->ItemKnwonId}, {value -> Map (key -> itemUnknownId) (value -> Distance between itemKnown and itemUnknown}
     *         This has size = limit
     */
    private Map<String,Map<String,Double>> getNeighbours(Map<String,Item> filteredKnownItems,Map<String,Item> unknownItems){
        Map<String,Double[]> attributesMinMax = findMinMaxOfItems(filteredKnownItems);
        Map<String,Map<String,Double>> neighbours = new HashMap<>();
        for(String itemId : filteredKnownItems.keySet()){
            Item knownItem = filteredKnownItems.get(itemId);
            Map<String,Double> nearestNeighbours = filterNearestNeighbours(getDistances(knownItem,unknownItems, attributesMinMax));
            neighbours.put(itemId,nearestNeighbours);
        }
        return neighbours;
    }

    private Double distanceStringAttributes(Map<String,String> knownItemAttributes, Map<String,String> unknownItemAttributes){
        Double distance = 0.0;
        for(String attributeName : unknownItemAttributes.keySet()){
            if(! knownItemAttributes.containsKey(attributeName) || knownItemAttributes.get(attributeName) != unknownItemAttributes.get(attributeName)){
                ++distance;
            }
        }
        return distance;
    }

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
        return distance;
    }

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
        return distance;
    }

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
        return distance;
    }


    /**
     *  Distances between a knwonItem and a Set(Map) of UnknownItems
     * @return
     */
    private Map<String,Double> getDistances(Item knownItem, Map<String,Item> unknownItems, Map<String,Double[]> attributesMinMax){
        Map<String,Double> distances = new HashMap<>();
        for(Item unknownItem : unknownItems.values()){

            Double currentDistance = distanceStringAttributes(knownItem.getStringAttributes(),unknownItem.getStringAttributes());
            currentDistance += distanceSetAttributes(knownItem.getSetAttributes(),unknownItem.getSetAttributes());
            currentDistance += distanceIntAttributes(knownItem.getIntAttributes(),unknownItem.getIntAttributes(),attributesMinMax);
            currentDistance += distanceDoubleAttributes(knownItem.getDoubleAttributes(),unknownItem.getDoubleAttributes(),attributesMinMax);
            distances.put(unknownItem.getId(),currentDistance);
        }

        return distances;
    }

    /**
     * Can be named recommend / executeAlgorithm ...
     * @param user
     * @param unknownItems
     * @param limit
     * @return
     */
    public Map<String,Double> query(User user, Map<String,Item> unknownItems, int limit){
        this.limit = limit;
        Map<String,Item> filteredKnownItems = user.getItems();//= filterKnownItems(user);
        Map<String,Map<String,Double>> neighbours = getNeighbours(filteredKnownItems,unknownItems);

        neighbours.forEach((k,v) -> {
            System.out.println("KnownItem ID :" + k);
            System.out.println("---------------------------");
            v.forEach((key,value) -> {
                System.out.println("UnknownItem ID : " + key + " - " + value);
            });
        });

        SortedMap<Double,String> queryResult = new TreeMap<>();


        return new HashMap<>();
    }
}
