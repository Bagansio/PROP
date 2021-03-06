package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.Models.*;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


/**
 *
 * @author Alex
 */
public class CtrlAlgorithms {
    private ContentBasedFiltering contentBasedFiltering = new ContentBasedFiltering();
    private NewCollaborativeFiltering collaborativeFiltering = new NewCollaborativeFiltering();
    private HybridFiltering hybridFiltering = new HybridFiltering();
    private static CtrlAlgorithms _instance = new CtrlAlgorithms();

    private CtrlAlgorithms(){
    }

    public static CtrlAlgorithms getInstance(){
        return _instance;
    }

    /**
     * @brief Get all the usable algorithms
     * @return An array with Algorithm Types
     */
    public String[] getAlgorithms(){
        return Stream.of(AlgorithmTypes.values()).map(AlgorithmTypes::name).toArray(String[]::new);
    }


    public String[] getPrecisions(){
        return Stream.of(PrecisionTypes.values()).map(PrecisionTypes::name).toArray(String[]::new);
    }
    /**
     * @brief Preprocess the needed data for correctly work of algorithms
     * @param itemMap The set of items
     * @param userMap The set of users
     */
    public void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap){
        collaborativeFiltering.preprocessingData(itemMap, userMap);
        contentBasedFiltering.preprocessingData(itemMap, userMap);
        hybridFiltering.setAlgorithms(contentBasedFiltering,collaborativeFiltering);
    }

    public Recommendation recommend(String algorithm,String precision, User currentUser, Map<String,Item> unknownItems){
        Recommendation recommendation = new Recommendation(CtrlRecommendations.getInstance().getNewId(),algorithm);
        recommendation.setUser(currentUser);
        recommendation.setPrecisionType(precision);
        switch (AlgorithmTypes.valueOf(algorithm)){
            case ContentBasedFiltering:
                recommendation.executeQuery(unknownItems,contentBasedFiltering,3);
                //rec = contentBasedFiltering.query(currentUser,unknownItems,3);
                break;
            case CollaborativeFiltering:
                recommendation.executeQuery(unknownItems,collaborativeFiltering,3);
                //rec = collaborativeFiltering.query(currentUser,unknownItems,3);
                break;
            case HybridApproach:
                recommendation.executeQuery(unknownItems,hybridFiltering,3);
            default:
                break;
        }

        Map<String,Double> rec = recommendation.getRecommendedItems();
        return recommendation;

    }
}
