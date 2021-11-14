package com.recommender.recommenderapp.Domain.Models;

import com.recommender.recommenderapp.Domain.Utils.ItemTypes;
import com.recommender.recommenderapp.Domain.Utils.PairDoubleDouble;
import com.recommender.recommenderapp.Domain.Utils.PairStringDouble;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.recommender.recommenderapp.Data.Controllers.CtrlCSVItemList.getItemList;
import static com.recommender.recommenderapp.Domain.Utils.ItemTypes.movie;

public class ContendBased {

    //Fund the min and max values for each numerical value related
    private Map<String,PairDoubleDouble> findMinMAx(Map<String, Item> items) {
        Map<String, PairDoubleDouble> minMax = new HashMap<String, PairDoubleDouble>();
        for (Map.Entry<String, Item> eachitem : items.entrySet()) {
            String key = eachitem.getKey();
            Item item = eachitem.getValue();
            Map<String, Integer> intAttributes = item.getIntAttributes();
            for (Map.Entry<String, Integer> attribute : intAttributes.entrySet()) {
                String name = attribute.getKey();
                Integer value = attribute.getValue();
                PairDoubleDouble valueminmax= minMax.get(name);
                if (valueminmax != null ){
                    if (valueminmax.x >value){
                        valueminmax.x = value.doubleValue();
                    }
                    if (valueminmax.y < value){
                        valueminmax.y = value.doubleValue();
                    }
                }
                else{
                    minMax.put(name, new PairDoubleDouble(value.doubleValue(),value.doubleValue()));
                }

            }
            Map<String, Double> doubleAttributes = item.getDoubleAttributes();
            for (Map.Entry<String, Double> attribute : doubleAttributes.entrySet()) {
                String name = attribute.getKey();
                Double value = attribute.getValue();
                PairDoubleDouble valueminmax= minMax.get(name);
                if (valueminmax != null ){
                    if (valueminmax.x >value){
                        valueminmax.x = value;
                    }
                    if (valueminmax.y < value){
                        valueminmax.y = value;
                    }
                }
                else{
                    minMax.put(name, new PairDoubleDouble(value,value));
                }

            }


        }
        return minMax;
    }
    //Rescale numerical values to the range 0-1

    //Locate k most similar neighbours
    private static List<PairStringDouble>  getNeighbours(Item itemTarget, Map<String, Item> itemList, int k){
        //PairStringDouble neighbour= new PairStringDouble();
        List<PairStringDouble> neighbours = new ArrayList<PairStringDouble>();
        List<PairStringDouble> result = new ArrayList<PairStringDouble>();
        AtomicReference<Double> distance = new AtomicReference<>(0.0);
        for(Map.Entry<String, Item> eachitem : itemList.entrySet()) {
            String key = eachitem.getKey();
            Item item = eachitem.getValue();
            Map<String,String> stringAttributes = item.getStringAttributes();
            Map<String,String> stringAttributesOfTarget = itemTarget.getStringAttributes();
            for(Map.Entry<String, String> attribute : stringAttributes.entrySet()) {
                String name = attribute.getKey();
                String value = attribute.getValue();
                String valueStringOfTarget= stringAttributesOfTarget.get(name);
                if (valueStringOfTarget != null){
                    if(valueStringOfTarget != value) { //we operate with each string and add 1 if they are equal
                        distance.getAndSet(distance.get() + 1);
                    }
                }



            }
            Map<String,Integer> intAttributes = item.getIntAttributes();
            Map<String,Integer> intAttributesOfTarget = itemTarget.getIntAttributes();
            for(Map.Entry<String, Integer> attribute : intAttributes.entrySet()) {
                String name = attribute.getKey();
                Integer value = attribute.getValue();
                Integer valueIntOfTarget= intAttributesOfTarget.get(name);
                if (valueIntOfTarget != null){
                    distance.getAndSet(distance.get() + Math.pow(valueIntOfTarget - value, 2));
                    System.out.println(key + name);
                    System.out.println(valueIntOfTarget);
                    System.out.println(value);
                    System.out.println(distance.get());
                    System.out.println("siguiente");
                }
                System.out.println("siguiente bucle");


            }

            Map<String,Double> doubleAttributes = item.getDoubleAttributes();
            Map<String,Double> doubleAttributesOfTarget = itemTarget.getDoubleAttributes();
            for(Map.Entry<String, Double> attribute : doubleAttributes.entrySet()) {
                String name = attribute.getKey();
                Double value = attribute.getValue();
                Double valueDoubleOfTarget= doubleAttributesOfTarget.get(name);
                if (valueDoubleOfTarget != null){
                    distance.getAndSet(distance.get() + Math.pow(valueDoubleOfTarget - value, 2));
                }

            }

            Map<String,Set<String>> setAttributes = item.getSetAttributes();
            Map<String,Set<String>> setAttributesOfTarget = itemTarget.getSetAttributes();

            for(Map.Entry<String, Set<String>> attribute : setAttributes.entrySet()) {
                String name = attribute.getKey();
                Set<String> value = attribute.getValue();
                Set<String> intersection = value;
                Set<String> valueSetOfTarget= setAttributesOfTarget.get(name);
                if (valueSetOfTarget != null) {
                    intersection.retainAll(valueSetOfTarget); // We do the intersection
                    Integer numberOfMatches = intersection.size();
                    Integer originalSize = valueSetOfTarget.size();
                    distance.getAndSet(distance.get() + ((originalSize - numberOfMatches) / originalSize)); // We divide by the original size in order to normalize the result
                }
            }
            Double finalDistance = distance.get(); // Get the reference of the atomic
            finalDistance = Math.sqrt(finalDistance);
            neighbours.add(new PairStringDouble(key,finalDistance));
        }

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
        int count = 0;
        for ( int i =0; i<k ; ++i){
            result.add(neighbours.get(i));
        }
    return result;
    }

    public static void main (String [] args) {
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
       List<PairStringDouble> neigh=  getNeighbours(itemTarget ,itemList, 2);
        System.out.println("test first neigh");
        System.out.println(neigh.get(0).x);
       System.out.println(neigh.get(0).y);

        System.out.println(neigh.get(1).x);
        System.out.println(neigh.get(1).y);
        System.out.println("WOrks?");

    }




}
