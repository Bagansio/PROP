package com.recommender.recommenderapp.Domain.Models;

import java.util.HashMap;
import java.util.Map;

public class ItemList {

    private Map<String,Item> items;
    private Map<String,Float> ratings;

    public ItemList() {
        this.items = new HashMap <String, Item>();
        this.ratings = new HashMap<String, Float>();
    }

    public void addItem (Item item){
        this.items.put(item.getId(),item);
    }


    public void quitItem (String itemId){
        this.items.remove(itemId);
    }

    public Item getItem(String itemId){
        return items.get(itemId);
    }
    public void rateItem (String itemId, Float rate){
        if(items.containsKey(itemId)){
            ratings.put(itemId,rate);
        }
    }

    public void setRate(String itemId, Float newRate){
        if(ratings.containsKey(itemId)) {
            ratings.replace(itemId, newRate);
        }
    }
}
