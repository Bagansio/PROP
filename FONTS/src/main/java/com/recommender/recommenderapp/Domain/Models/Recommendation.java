package com.recommender.recommenderapp.Domain.Models;

import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Artur
 */
public class Recommendation {
    private String id;
    private User user;
    private int score;
    private PrecisionTypes precisionType; //will be set after loading the data. Main should inform the user;
    /*
    Choose dataset
    sout("Program is loading and processing the specified data. This may take a while")
    recommendation.preprocessData()
    Choose precision
     */

    static private Map<String, Item> itemMap;
    private Map<String, Double> recommendedItems;
    private AlgorithmTypes algorithmType;

    /**
     * Basic constructor operation
     */
    public Recommendation() {}

    /**
     *
     * @param id -> identifies the recommendation
     * @param algorithm -> assigns an algorithm to the recommendation
     */
    public Recommendation(String id, String algorithmType) {
        this.id = id;
        this.algorithmType = AlgorithmTypes.valueOf(algorithmType);
    }

    public String getAlgorithmType(){
        return algorithmType.toString();
    }

    public void setAlgorithmType(String algorithmType){
        this.algorithmType = AlgorithmTypes.valueOf(algorithmType);
    }

    /**
     *
     * @return score given by the user
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score -> score assigned by the user to the recommendation
     */
    public void setScore(int score) {
        this.score = score;
    }

    public Map<String,Double> getRecommendedItems(){
        return recommendedItems;
    }

    /**
     *
     * @return the user that the recommendation is for
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user -> user that the recommendation is done for
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return the identifier of the recommendation
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id -> identifier of the recommendation
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the map of items used in the recommendation
     */
    public static Map<String, Item> getItemMap() {
        return itemMap;
    }

    /**
     *
     * @param itemMap -> map of items that will be used in the recommendation
     */
    public static void setItemMap(Map<String, Item> itemMap) {
        Recommendation.itemMap = itemMap;
    }

    /**
     *
     * @return the set precision of the recommendation
     */
    public String getPrecisionType() {
        return precisionType.toString();
    }

    /**
     *
     * @param precisionType -> specifies the amount of precision desired by the user
     */
    public void setPrecisionType(String precisionType) {

        this.precisionType = PrecisionTypes.valueOf(precisionType);
    }

    /**
     *
     * @param user -> user that contains the known ratings
     * @param unknownItems -> set of unknown items
     * @param Q -> amount of items that shall be recommended
     */
    public void executeQuery(Map<String, Item> unknownItems, Algorithm algorithm,  int Q) {
        switch (this.precisionType) {
            case imprecise:
                queryPrecision(user, algorithm,unknownItems, Q, 1);
                break;
            case precise:
                queryPrecision(user,algorithm, unknownItems, Q, 5);
                break;
            default:
                queryPrecision(user,algorithm, unknownItems, Q, 10);
                break;
        }
    }

    /**
     *
     * @param user -> unknown user
     * @param unknownItems -> Map - key (itemId) value (item)
     * @param Q -> number of items that must be recommended
     * @param precision -> precision chosen by the user
     */
    private void queryPrecision(User user,Algorithm algorithm, Map<String, Item> unknownItems, int Q, int precision) {
        Map<String, Double> itemRatings = new LinkedHashMap<>();
        Map<String, Integer> itemAppearances = new LinkedHashMap<>();

        for (String key: unknownItems.keySet()) {
            itemRatings.put(key, 0.0);
            itemAppearances.put(key, 0);
        }

        for (int i = 0; i < precision; ++i) {
            Map<String, Double> auxRatings = new HashMap<>();
            auxRatings = algorithm.query(user, unknownItems, Q);
            for (String key: auxRatings.keySet()) {
                itemAppearances.replace(key, itemAppearances.get(key) + 1);
                itemRatings.replace(key, itemRatings.get(key) + auxRatings.get(key));
            }
        }

        for (String key: itemRatings.keySet()) {
            if (itemAppearances.get(key) != 0) {
                itemRatings.replace(key, itemRatings.get(key) / itemAppearances.get(key).doubleValue());
            }
        }
        recommendedItems = limitRecommendedItems(itemRatings, Q);

    }

    /**
     *
     * @param recommendedItems -> Map - key(unknownItemId) value (expectedRating)
     * @param Q -> number of items that shall be recommended
     * @return a map that contains the Q best rated items
     */
    private Map<String, Double> limitRecommendedItems(Map<String, Double> recommendedItems, int Q) {
        Map<String, Double> result = new LinkedHashMap<>();

        recommendedItems = recommendedItems.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Iterator<Map.Entry<String, Double>> it = recommendedItems.entrySet().iterator();

        int i = 0;
        while (i < Q && it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
            ++i;
        }

        return result;
    }


    /**
     *
     * @param unknown -> user that contains the rating of the unknown items
     * @return the normalized discounted cumulative gain of the used algorithm
     */
    public Double normalizedDiscountedCumulativeGain(User unknown) {
        return Algorithm.discountedCumulativeGain(recommendedItems, unknown);
    }

    public void setRecommendedItems(Map<String,Double> recommendedItems){
        this.recommendedItems = recommendedItems;
    }


    /*public void preprocessData(Map<String, User> userMap) {
        switch(this.algorithmType) {
            case CollaborativeFiltering:
                algorithm.preprocessingData(itemMap, userMap);
                break;
            case ContentBasedFiltering:
                algorithm.preprocessingData(itemMap, userMap);
                break;
            default:
                //HAF.preprocessingData(userMap);
                break;
        }
    }

    public Map<String, Double> query(User user, Map<String, Item> unknownItems, int Q) {
        Map<String, Double> recommendedItems = new HashMap<>();
        switch (this.algorithmType) {
            case CollaborativeFiltering:
                recommendedItems = algorithm.query(user, unknownItems, Q);
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

    public Map<String, Double> CFQuery(User user, Map<String, Item> unknownItems, int Q) {
        Map<String, Double> recommendedItems = new HashMap<>();
        Map<String, Double> auxRecommendedItems = new HashMap<>();
        Double aux;
        for (String key : unknownItems.keySet()) {
            recommendedItems.put(key, 0.0);
        }

        switch (this.precisionType) {

            case imprecise:
                recommendedItems = CF.query(user, unknownItems, Q);
                break;
            case precise:
                for (int i = 0; i < 5; ++i) {
                    auxRecommendedItems = CF.query(user, unknownItems, Q);
                    for (String key: auxRecommendedItems.keySet()) {
                        aux = recommendedItems.get(key);
                        recommendedItems.put(key, aux + auxRecommendedItems.get(key));
                    }
                }
            case veryPrecise:
                for (int i = 0; i < 10; ++i) {
                    auxRecommendedItems = CF.query(user, unknownItems, Q);
                    for (String key: auxRecommendedItems.keySet()) {
                        aux = recommendedItems.get(key);
                        recommendedItems.put(key, aux + auxRecommendedItems.get(key));
                    }

                }
        }

        return recommendedItems;
    }

    public Map<String, Double> CBFQuery(User user, Map<String, Item> unknownItems, int Q) {}

    //public Map<String, Double> HAQuery(User user, Map<String, Item> unknownItems, int Q) {}

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
    } */

}
