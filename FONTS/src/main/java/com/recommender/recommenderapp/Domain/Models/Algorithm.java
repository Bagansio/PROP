package com.recommender.recommenderapp.Domain.Models;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Artur Farriols
 */
public abstract class Algorithm {

    protected int kmeans;

    protected int knn;

    //Constructor

    /**
     * creates an instance of the class
     */
    public Algorithm() {
        this.kmeans = 3;
        this.knn = 10;
    }

    //getters

    /**
     *
     * @return the value of the K of the Kmeans algorithm
     */
    public int getKmeans() {
        return kmeans;
    }

    /**
     *
     * @return the value for the K of the Knn algorithm
     */
    public int getKnn() {
        return knn;
    }

    /**
     * executes all the operations and methods in order to apply the algorithms
     */
    public abstract void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap);

    /**
     *
     * @param user -> user that contains all the known items and ratings
     * @param unknownItems -> set of unknown items that the algorithms should operate with
     * @param Q -> number of items that the algorithm should recommend
     * @return the ratings that the algorithms have assigned to Q unknown items
     */
    public abstract Map<String, Double> query(User user, Map<String, Item> unknownItems, int Q);

    /**
     *
     * @param recommendedItems -> set of items rated by the algorithms
     * @param unknown -> user that contains the rating of all the unknown items involved in the Query function
     * @return the Normalized Discounted Cumulative Gain
     */
    public static Double discountedCumulativeGain(Map<String, Double> recommendedItems, User unknown) {
        Map<String, Double> unknownOrderedRatings = new LinkedHashMap<>();
        Map<String, Double> unknownRatings = unknown.getRatings();

        unknownRatings.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Iterator<Map.Entry<String,Double>> it = unknownRatings.entrySet().iterator();

        int i = 0;

        while(i < recommendedItems.size() && it.hasNext()){
            Map.Entry<String,Double> entry = it.next();
            unknownOrderedRatings.put(entry.getKey(),entry.getValue());
            ++i;
        }

        Double DCG = 0.0;
        Integer counter = 1;
        for (String unknownItemId: recommendedItems.keySet()) {
            double value = 2.0;
            value = Math.pow(value, unknownRatings.get(unknownItemId)) - 1.0;
            value = value / (Math.log((counter.doubleValue() + 1.0)) / Math.log(2.0));

            DCG += value;
            ++counter;
        }

        Double IDCG = 0.0;
        counter = 1;
        for (String unknownItemId: unknownOrderedRatings.keySet()) {
            double value = 2.0;
            value = Math.pow(value, unknownRatings.get(unknownItemId)) - 1.0;
            value = value / (Math.log((counter.doubleValue() + 1.0)) / Math.log(2.0));

            IDCG += value;
            ++counter;
        }

        System.out.println("DCG value is: " + DCG);
        System.out.println("IDCG value is: " + IDCG);
        return DCG / IDCG;
    }

}
