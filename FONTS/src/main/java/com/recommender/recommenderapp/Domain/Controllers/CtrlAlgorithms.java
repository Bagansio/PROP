package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.Models.ContentBasedFiltering;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.NewCollaborativeFiltering;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;

import java.util.HashMap;
import java.util.Map;

public class CtrlAlgorithms {
    private ContentBasedFiltering contentBasedFiltering = new ContentBasedFiltering();
    private NewCollaborativeFiltering collaborativeFiltering = new NewCollaborativeFiltering();
    private static CtrlAlgorithms _instance = new CtrlAlgorithms();

    private CtrlAlgorithms(){
    }

    public static CtrlAlgorithms getInstance(){
        return _instance;
    }

    /**
     * @brief Preprocess the needed data for correctly work of algorithms
     * @param itemMap The set of items
     * @param userMap The set of users
     */
    public void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap){
        collaborativeFiltering.preprocessingData(itemMap, userMap);
        contentBasedFiltering.preprocessingData(itemMap, userMap);
    }

    public String[] recommend(String algorithm,User currentUser, Map<String,Item> unknownItems){
        String[] recommendation = new String[0];
        Map<String,Double> rec = new HashMap<>();
        switch (AlgorithmTypes.valueOf(algorithm)){
            case ContentBasedFiltering:
                rec = contentBasedFiltering.query(currentUser,unknownItems,3);
                break;
            case CollaborativeFiltering:
                rec = collaborativeFiltering.query(currentUser,unknownItems,3);
                break;
            default:
                break;
        }
        System.out.println("USING " + algorithm);
        for(String item : rec.keySet()){
            System.out.println(item + ": " + rec.get(item));
        }
        System.out.println("---------------");
        return recommendation;

    }
}
