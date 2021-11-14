package com.recommender.recommenderapp.Domain.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
public class User {

    private String id;
    private String name;
    private Map<String, Item> items;
    private Map<String, Double> ratings;


    public User(String id) {
        this.id = id;
        this.items = new HashMap<>();
        this.ratings = new HashMap<>();
    }

    public void addItem(Item item) {
        this.items.put(item.getId(), item);
    }

    public void quitItem(String itemId) {
        this.items.remove(itemId);
    }

    public Item getItem(String itemId) {
        return items.get(itemId);
    }

    public void rateItem(String itemId, Double rate) {
            ratings.put(itemId, rate);
    }

    public void setRate(String itemId, Double newRate) {
        if (ratings.containsKey(itemId)) {
            ratings.replace(itemId, newRate);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public Map<String, Double> getRatings() {
        return ratings;
    }

}