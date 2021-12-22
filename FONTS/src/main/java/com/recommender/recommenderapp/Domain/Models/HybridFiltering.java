package com.recommender.recommenderapp.Domain.Models;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author  Alex
 */
public class HybridFiltering extends Algorithm{

    ContentBasedFiltering contentBasedFiltering;
    NewCollaborativeFiltering newCollaborativeFiltering;

    public  void setAlgorithms(ContentBasedFiltering contentBasedFiltering, NewCollaborativeFiltering newCollaborativeFiltering){
        this.contentBasedFiltering = contentBasedFiltering;
        this.newCollaborativeFiltering =  newCollaborativeFiltering;
    }


    public void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap) {

    }

    public Map<String,Double> query(User user, Map<String, Item> unknownItems, int Q){

        Map<String,Double> queryContent = contentBasedFiltering.query(user,unknownItems, Q);
        Map<String,Double> queryCollaborative = newCollaborativeFiltering.query(user,unknownItems, Q);

        Map<String,Double> result = queryContent;

        for(String itemId : queryCollaborative.keySet()){
            if(result.containsKey(itemId)){
                Double expectedRate = (result.get(itemId) + queryCollaborative.get(itemId))/2;
                result.replace(itemId, expectedRate);
            }
            else{
                result.put(itemId,queryCollaborative.get(itemId));
            }
        }


        result = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result;
    }
}
