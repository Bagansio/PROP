package com.recommender.recommenderapp.Domain.Models;

import com.recommender.recommenderapp.Domain.Utils.PairDoubleDouble;
import com.recommender.recommenderapp.Domain.Utils.PairStringDouble;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.recommender.recommenderapp.Domain.Utils.ItemTypes.movie;

public class ContendBased {


    //Find the min and max values for each numerical value related
    private static Map<String,PairDoubleDouble> findMinMAx(Map<String, Item> items) {
        Map<String, PairDoubleDouble> minMax = new HashMap<String, PairDoubleDouble>();
        for (Map.Entry<String, Item> currentItem : items.entrySet()) { // Loop for each item in items
            String key = currentItem.getKey();
            Item item = currentItem.getValue();
            Map<String, Integer> intAttributes = item.getIntAttributes();
            for (Map.Entry<String, Integer> attribute : intAttributes.entrySet()) {//Loop for each integer attribute
                String name = attribute.getKey();
                Integer value = attribute.getValue();
                PairDoubleDouble valueMinMax= minMax.get(name);
                if (valueMinMax != null ){// if minMax(name) exists we change the min and/or max if needed
                    if (valueMinMax.x >value){
                        valueMinMax.x = value.doubleValue();
                    }
                    if (valueMinMax.y < value){
                        valueMinMax.y = value.doubleValue();
                    }
                }
                else{// if not exists we create an instance with min and max equal to value
                    minMax.put(name, new PairDoubleDouble(value.doubleValue(),value.doubleValue()));
                }

            }
            Map<String, Double> doubleAttributes = item.getDoubleAttributes();
            for (Map.Entry<String, Double> attribute : doubleAttributes.entrySet()) {//Loop for each double attribute
                String name = attribute.getKey();
                Double value = attribute.getValue();
                PairDoubleDouble valueMinMax= minMax.get(name);
                if (valueMinMax != null ){// if minMax(name) exists we change the min and/or max if needed
                    if (valueMinMax.x >value){
                        valueMinMax.x = value;
                    }
                    if (valueMinMax.y < value){
                        valueMinMax.y = value;
                    }
                }
                else{// if not exists we create an instance with min and max equal to value
                    minMax.put(name, new PairDoubleDouble(value,value));
                }

            }


        }
        return minMax;
    }
    //Rescale numerical values to the range 0-1
    private static Double normalizeValue(Double value, Double min , Double max){
       /* System.out.println("minMaxyeso ");
        System.out.println(value);
        System.out.println(min);
        System.out.println(max);
        System.out.println((value - min) / (max-min));*/
        if(max>min){ // if max != min we normalize value
            return ((value - min) / (max-min));
        }
        else { //if min == max we return max because all the attributes of this type are equal
            return max;
        }

    }
    //Function to sort the neighbours in ascending order according to the distance between the items
    private static void sortNeighbours(List<PairStringDouble> neighbours){
        Collections.sort(neighbours, new Comparator<PairStringDouble>() {
            @Override
            public int compare(final PairStringDouble n1, final PairStringDouble n2) {
                if(n1.y < n2.y){
                    return -1;
                }
                else if (n1.y == n2.y){
                    return 0;
                }
                else {
                    return 1;
                }
            }
        });

    }

    //Locate k most similar neighbours of the itemTarget
    private static List<PairStringDouble>  getNeighboursOfItem(Item itemTarget, Map<String, Item> itemList, int k, Map<String,PairDoubleDouble> minMax){
        List<PairStringDouble> neighbours = new ArrayList<PairStringDouble>();
        List<PairStringDouble> result = new ArrayList<PairStringDouble>();
        AtomicReference<Double> distance = new AtomicReference<>(0.0); // We initialize distance at 0, and then we add every distance between attributes
        for(Map.Entry<String, Item> currentItem : itemList.entrySet()) { // Loop for each item in itemList
            String key = currentItem.getKey();
            Item item = currentItem.getValue();
            Map<String,String> stringAttributes = item.getStringAttributes();
            Map<String,String> stringAttributesOfTarget = itemTarget.getStringAttributes();
            for(Map.Entry<String, String> attribute : stringAttributes.entrySet()) { // Loop for each String Attribute
                String name = attribute.getKey();
                String value = attribute.getValue();
                String valueStringOfTarget= stringAttributesOfTarget.get(name);
                if (valueStringOfTarget != null){ // we operate if the item that we are comparing has an attribute of the same type as the String attribute we are looking
                    if(valueStringOfTarget != value) { //if the value of the attribute is different we add 1 to the distance, else we do nothing because there is no distance between them
                        distance.getAndSet(distance.get() + 1);
                    }
                }



            }
            Map<String,Integer> intAttributes = item.getIntAttributes();
            Map<String,Integer> intAttributesOfTarget = itemTarget.getIntAttributes();
            for(Map.Entry<String, Integer> attribute : intAttributes.entrySet()) { //Loop for each Integer attribute
                String name = attribute.getKey();
                Integer value = attribute.getValue();
                Integer valueIntOfTarget= intAttributesOfTarget.get(name);
                if (valueIntOfTarget != null){ // we operate if the item that we are comparing has an attribute of the same type as the Integer attribute we are looking
                    PairDoubleDouble minMaxOfAttrib = minMax.get(name);
                    distance.getAndSet(distance.get() + Math.pow(normalizeValue(valueIntOfTarget.doubleValue(), minMaxOfAttrib.x, minMaxOfAttrib.y) - normalizeValue(value.doubleValue(), minMaxOfAttrib.x, minMaxOfAttrib.y), 2)); //we calculate the euclidean distance between the two numerical values(we normalize the values in order to get a better performance)

                }



            }

            Map<String,Double> doubleAttributes = item.getDoubleAttributes();
            Map<String,Double> doubleAttributesOfTarget = itemTarget.getDoubleAttributes();
            for(Map.Entry<String, Double> attribute : doubleAttributes.entrySet()) {//Loop for each Double attribute
                String name = attribute.getKey();
                Double value = attribute.getValue();
                Double valueDoubleOfTarget= doubleAttributesOfTarget.get(name);
                if (valueDoubleOfTarget != null){ // we operate if the item that we are comparing has an attribute of the same type as the Double attribute we are looking
                    PairDoubleDouble minMaxOfAttrib = minMax.get(name);
                    distance.getAndSet(distance.get() + Math.pow(normalizeValue(valueDoubleOfTarget, minMaxOfAttrib.x, minMaxOfAttrib.y) - normalizeValue(value, minMaxOfAttrib.x, minMaxOfAttrib.y), 2)); //we calculate the euclidean distance between the two numerical values(we normalize the values in order to get a better performance)
                }

            }

            Map<String,Set<String>> setAttributes = item.getSetAttributes();
            Map<String,Set<String>> setAttributesOfTarget = itemTarget.getSetAttributes();

            for(Map.Entry<String, Set<String>> attribute : setAttributes.entrySet()) { //Loop for each Set attribute
                String name = attribute.getKey();
                Set<String> value = attribute.getValue();
                Set<String> intersection = value;
                Set<String> valueSetOfTarget= setAttributesOfTarget.get(name);
                if (valueSetOfTarget != null) { // we operate if the item that we are comparing has an attribute of the same type as the Set attribute we are looking
                    intersection.retainAll(valueSetOfTarget); // We do the intersection
                    Integer numberOfMatches = intersection.size(); //Count of the number of matches
                    Integer originalSize = valueSetOfTarget.size();
                    distance.getAndSet(distance.get() + ((originalSize - numberOfMatches) / originalSize)); // We calculate the number of different values and then we divide by the original Size in order to normalize the result
                }
            }
            Double finalDistance = distance.get(); // Get the reference of the atomic
            finalDistance = Math.sqrt(finalDistance); //we do the square root of the distance
            neighbours.add(new PairStringDouble(key,finalDistance)); // we add in neigbhours a map with the key of the item and the distance between this item and the target
        }
        sortNeighbours(neighbours); // We sort the neighbours in ascending order according to the distance between the items

        int count = 0;
        for ( int i =0; i<k ; ++i){ // Now with the items sorted we return the k-first neighbours the ones with less distance
            result.add(neighbours.get(i));
        }
    return result;
    }
    //Locate the k-closest neighbours from a set of distances
    private static List<PairStringDouble> findNearestNeighbours( Map<String, List<PairStringDouble>> neighbours, int k){ // We encapsulate this function in order to increase the changeability, in future deliveries we will implement this is a new class.
        //This algorithm will take the k neighbours with less distance to any item.
        List<PairStringDouble> result = new ArrayList<PairStringDouble>();
        List<PairStringDouble> allNeighbours = new ArrayList<PairStringDouble>();
        for (Map.Entry <String, List<PairStringDouble>> currentNeighbour : neighbours.entrySet()) { // loop for each rating
            String name = currentNeighbour.getKey();
            List<PairStringDouble> value = currentNeighbour.getValue();
            allNeighbours.addAll(value); // we join all the neighbours in one list we just focus on the neighbours with less distance
        }
        sortNeighbours(allNeighbours); // We sort result in ascending order according to the distance between the items
        Boolean stop = false;
        boolean follow = true;
        for(int i=0; i< allNeighbours.size() && stop; ++i){
            if (i==0){
                result.add(allNeighbours.get(i)); //the first item will be the closest one(less distance)
            }
            else {
               follow = true;
                PairStringDouble currentNeighbour= allNeighbours.get(i);
                for (int j =0; j<result.size() && follow;++j){ // we iterate to find if currentNeighbour is in result
                    if(currentNeighbour.x == result.get(j).x ){
                        follow=false;
                    }
                }
                if (follow) { //if currentNeighbour is not in result follow will be true and we add currentNeighbour to the result
                    result.add(allNeighbours.get(i));
                }
            }
            if (result.size() >= k){ // if result.size == k we find the k-nearest neighbours and we are done
                stop = true;
            }
        }
        return result;
    }


    //Find the k-closest neighbours of the User
    private static List<PairStringDouble> getNeighboursOfUser(User userTarget, int k) {
        List<PairStringDouble> result = new ArrayList<PairStringDouble>();
        Map<String, Double> ratings = userTarget.getRatings();
        Map<String, Item> items = userTarget.getItems();
        Map<String, List<PairStringDouble>> neighbours = new HashMap<String, List<PairStringDouble>>();
        Double min = 0.0;
        Double max = 0.0;
        Map<String, PairDoubleDouble> minMax = findMinMAx(items);
        for (Map.Entry<String, Double> currentRating : ratings.entrySet()) { // we find the min and max values
            String name = currentRating.getKey();
            Double value = currentRating.getValue();
            if (max < value) {
                max = value.doubleValue();
            } else if (min > value) {
                min = value.doubleValue();
            }
        }
        for (Map.Entry<String, Double> currentRating : ratings.entrySet()) { // loop for each rating
            String name = currentRating.getKey();
            Double value = currentRating.getValue();
            if (value >= (max - min) * 0.5) { //if value is greater than the mean of values we calculate their neighbours
                Item item = items.get(name);
                Map<String, Item> itemList = items;
                itemList.remove(name);
                neighbours.put(name, getNeighboursOfItem(item, itemList, k, minMax));
            }


        }
        result = findNearestNeighbours(neighbours, k);// Once we have all the items that we want to focus on with her k nearest neighbours we find the k nearest neighbour of the user

        return result;
    }

    public static void main (String [] args) {
        //Tests
       Map<String, Item> itemList = new HashMap<String, Item>();
       Item test1 = new Item("test1", "test1", movie);
       Item test2 = new Item("test2", "test2", movie);
       Item test3 = new Item("test3", "test3", movie);
       Item test4 = new Item("test4", "test4", movie);
       Item test5 = new Item("test5", "test5", movie);
       Item test6 = new Item("test6", "test6", movie);
       Item test7 = new Item("test7", "test7", movie);

       Map<String,String> testStringAttributes1 = new HashMap<String, String>();
        Map<String,String> testStringAttributes2 = new HashMap<String, String>();
       Map<String,Integer> testIntAttributes1 = new HashMap<String, Integer>();
        Map<String,Integer> testIntAttributes2 = new HashMap<String, Integer>();
       Map<String,Double> testDoubleAttributes = new HashMap<String, Double>();
       Map<String,Set<String>> testSetAttributes = new HashMap<String, Set<String>>();

       testStringAttributes1.put("testAttrib1","false");
       testStringAttributes1.put("testAttrib2","test");

        testStringAttributes2.put("testAttrib1","test");
        testStringAttributes2.put("testAttrib2","test");

       testIntAttributes1.put("testAttrib1",1);
       testIntAttributes1.put("testAttrib2",2);

        testIntAttributes2.put("testAttrib1",1);
        testIntAttributes2.put("testAttrib2",2);

       testDoubleAttributes.put("testAttrib1",1.0);
       testDoubleAttributes.put("testAttrib2",2.0);

       Set<String> testAttribSet1 = new HashSet<String>();
       testAttribSet1.add("test");
       testAttribSet1.add("helloWorld");

       Set<String> testAttribSet2 = new HashSet<String>();
       testAttribSet2.add("test");
       testAttribSet2.add("helloWorld");
       testSetAttributes.put("testAttrib1", testAttribSet1);
       testSetAttributes.put("testAttrib2", testAttribSet2);

       test1.setStringAttributes(testStringAttributes1);
       test1.setIntAttributes(testIntAttributes1);
       test1.setDoubleAttributes(testDoubleAttributes);
       test1.setSetAttributes(testSetAttributes);


       testIntAttributes2.put("testAttrib3",3);
       testIntAttributes2.put("testAttrib2",5);


       test2.setStringAttributes(testStringAttributes2);
       test2.setIntAttributes(testIntAttributes2);
       test2.setDoubleAttributes(testDoubleAttributes);
       test2.setSetAttributes(testSetAttributes);



       itemList.put("test1",test1);
       itemList.put("test2",test2);
       Item itemTarget = test2;
       List<PairStringDouble> neigh=  getNeighboursOfItem(itemTarget ,itemList, 2, findMinMAx(itemList));
        System.out.println("test first neigh");
        System.out.println(neigh.get(0).x);
       System.out.println(neigh.get(0).y);

        System.out.println(neigh.get(1).x);
        System.out.println(neigh.get(1).y);

        /*
        Map<String, Item> items = User.getItems();

        Item itemTargetFinal = items.get(858);

        List<PairStringDouble> neighbour=  getNeighbours(itemTargetFinal ,items, 2, findMinMAx(items));
        System.out.println("test neigh with all ");
        System.out.println(neighbour.get(0).x);
        System.out.println(neighbour.get(0).y);

        System.out.println(neighbour.get(1).x);
        System.out.println(neighbour.get(1).y);
        System.out.println("Maybe works");

*/


    }




}
