package com.recommender.recommenderapp.Domain.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
public class User {

    private String id;
    private String name;
    private final Map<String, Item> items;
    private final Map<String, Float> ratings;


    public User(String id) {
        this.id = id;
        this.items = new HashMap<String, Item>();
        this.ratings = new HashMap<String, Float>();
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

    public void rateItem(String itemId, Float rate) {
        if (items.containsKey(itemId)) {
            ratings.put(itemId, rate);
        }
    }

    public void setRate(String itemId, Float newRate) {
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

    public Map<String, Float> getRatings() {
        return ratings;
    }

}