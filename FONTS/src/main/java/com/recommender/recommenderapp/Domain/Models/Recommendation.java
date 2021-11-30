package com.recommender.recommenderapp.Domain.Models;

import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.HashMap;
import java.util.Map;

public class Recommendation {
    private String id;
    private User user;
    private int score;

    private AlgorithmTypes algorithmType;

    private PrecisionTypes precisionType; //will be set after loading the data. Main should inform the user;
    /*
    Choose dataset
    sout("Program is loading and processing the specified data. THis may take a while")
    recommendation.preprocessData()
    Choose precision
     */


    private Map<String, Double> recommendedItems;
    private NewCollaborativeFiltering CF;
    private ContentBasedFiltering CBF;
    //HybridApproach HAF;

    public Recommendation() {
        recommendedItems = new HashMap<>();
    }

    public Recommendation(String id, AlgorithmTypes algorithmType) {
        this.id = id;
        this.algorithmType = algorithmType;
        recommendedItems = new HashMap<>();

        switch (this.algorithmType) {
            case CollaborativeFiltering:
                CF = new NewCollaborativeFiltering();
                break;
            case ContentBasedFiltering:
                CBF = new ContentBasedFiltering();
            default:
                //HAF = new HybridApproachFiltering();
                break;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlgorithmTypes getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(AlgorithmTypes type) {
        this.algorithmType = type;
    }

    public PrecisionTypes getPrecisionType() {
        return precisionType;
    }

    public void setPrecisionType(PrecisionTypes precisionType) {
        this.precisionType = precisionType;
    }

    public void preprocessData(Map<String, Item> itemMap, Map<String, User> userMap) {
        switch(this.algorithmType) {
            case CollaborativeFiltering:
                CF.preprocessingData(itemMap, userMap);
                break;
            case ContentBasedFiltering:
                CBF.preprocessingData(itemMap, userMap);
                break;
            default:
                //HAF.preprocessingData(itemMap, userMap);
                break;
        }
    }

    public Map<String, Double> query(User user, Map<String, Item> unknownItems, int Q) {
        Map<String, Double> recommendedItems = new HashMap<>();
        switch (this.algorithmType) {
            case CollaborativeFiltering:
                recommendedItems = CF.query(user, unknownItems, Q);
                break;
            case ContentBasedFiltering:
               recommendedItems = CBF.query(user, unknownItems, Q);
                break;
            default:
                //recommendedItems = HAF.query(user, unknownItems, Q);
                break;
        }

        this.recommendedItems = recommendedItems;
        return recommendedItems;
    }

    public Double normalizedDiscountedCumulativeGain() {
        Double NDCG;
        switch (this.algorithmType) {
            case CollaborativeFiltering:
                NDCG = CF.discountedCumulativeGain(this.recommendedItems, user);
                break;
            case ContentBasedFiltering:
                NDCG = CBF.discountedCumulativeGain(this.recommendedItems, user);
                break;
            default:
                NDCG = 1.0;
                //HAF.discountedCumulativeGain(this.recommendedItems, user)
                break;
        }

        return NDCG;
    }

    private Map<String, Double > getRatings() {
        Map<String, Double> ratedItems = new HashMap<>();

        return ratedItems;
    }

}
