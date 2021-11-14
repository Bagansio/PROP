package com.recommender.recommenderapp.Domain.Models;

import com.recommender.recommenderapp.Domain.Utils.PairStringFloat;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ContendBased {
    //Euclidean Distance
    //Change String to Int
    //Locate k most similar neighbours
    private List<PairStringFloat>  getNeighbours(Item itemTarget, Map<String, Item> itemList, int k){
        //PairStringFloat neighbour= new PairStringFloat();
        List<PairStringFloat> neighbours = new ArrayList<PairStringFloat>();
        List<PairStringFloat> result = new ArrayList<PairStringFloat>();
        AtomicReference<Double> distance = new AtomicReference<>(0.0);
        itemList.forEach((key,item)->{ // loop for each itemList
            Map<String,String> stringAttributes = item.getStringAttributes();
            for(Map.Entry<String, String> attribute : stringAttributes.entrySet()) {
                String name = attribute.getKey();
                String value = attribute.getValue();
                Map<String,String> stringAttributesOfTarget = itemTarget.getStringAttributes();
                String valueStringOfTarget= stringAttributesOfTarget.get(name);
                //we operate with each string and add 1 if they are equal
                if(valueStringOfTarget != value) {
                    distance.getAndSet(distance.get() + 1);
                }
            }
            Map<String,Integer> intAttributes = item.getIntAttributes();
            for(Map.Entry<String, Integer> attribute : intAttributes.entrySet()) {
                String name = attribute.getKey();
                Integer value = attribute.getValue();
                Map<String,Integer> intAttributesOfTarget = itemTarget.getIntAttributes();
                Integer valueIntOfTarget= intAttributesOfTarget.get(name);
                distance.getAndSet(distance.get() + Math.pow(valueIntOfTarget - value, 2));
            }

            Map<String,Float> floatAttributes = item.getFloatAttributes();
            for(Map.Entry<String, Float> attribute : floatAttributes.entrySet()) {
                String name = attribute.getKey();
                Float value = attribute.getValue();
                Map<String,Float> floatAttributesOfTarget = itemTarget.getFloatAttributes();
                Float valueFloatOfTarget= floatAttributesOfTarget.get(name);
                distance.getAndSet(distance.get() + Math.pow(valueFloatOfTarget - value, 2));

            }

            Map<String,Set<String>> setAttributes = item.getSetAttributes();
            for(Map.Entry<String, Set<String>> attribute : setAttributes.entrySet()) {
                String name = attribute.getKey();
                Set<String> value = attribute.getValue();
                Map<String,Set<String>> floatAttributesOfTarget = itemTarget.getSetAttributes();


            }
        });





        }

        int count = 0;
        for ( int i =0; i<k ; ++i){
            result.add(neighbours.get(i));
        }
    return result;
    }





}
