package com.recommender.recommenderapp.Domain.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Melanie Scatena
 */
public class User {

    private String id;
    private String name;
    private Map<String, Item> items;
    private Map<String, Double> ratings;

    /**
     *
     * @param id -> identifier of the user
     */
    public User(String id) {
        this.id = id;
        this.items = new HashMap<>();
        this.ratings = new HashMap<>();
    }

    /**
     *
     * @param item -> item that will be added to the items rated by the user
     */
    public void addItem(Item item) {
        this.items.put(item.getId(), item);
    }

    /**
     *
     * @param itemId -> item identifier from the item that will be removed from the items rated by the user
     */
    public void quitItem(String itemId) {
        this.items.remove(itemId);
    }

    /**
     *
     * @param itemId -> item identifier
     * @return item that corresponds with the one identified by the parameter
     */
    public Item getItem(String itemId) {
        return items.get(itemId);
    }

    /**
     *
     * @param ratings -> set of ratings that will be added to the user
     */
    public void setRatings(Map<String, Double> ratings) {
        this.ratings = ratings;
    }

    /**
     *
     * @param itemId -> identifier of the item that will be rated by the user
     * @param rate -> rating of the identified item
     */
    public void rateItem(String itemId, Double rate) {
        ratings.put(itemId, rate);
    }

    /**
     *
     * @param itemId -> identifier of the item that will be rated by the user
     * @param newRate -> rate that will substitute the prior
     */
    public void setRate(String itemId, Double newRate) {
        if (ratings.containsKey(itemId)) {
            ratings.replace(itemId, newRate);
        }
    }

    /**
     *
     * @return identifier of the user
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id -> identifier that will be assigned to the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name -> name that will be assigned to the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return items rated by the user
     */
    public Map<String, Item> getItems() {
        return items;
    }

    /**
     *
     * @return ratings of the user
     */
    public Map<String, Double> getRatings() {
        return ratings;
    }


    public Map<String,Double> searchRatings(String id){

        Map<String,Double> result = new HashMap<>();
        for(Item item : items.values()){
            if(item.getTitle().contains(id)) {
                result.put(item.getId(), ratings.get(item.getId()));
            }
        }
        return result;
    }

}
