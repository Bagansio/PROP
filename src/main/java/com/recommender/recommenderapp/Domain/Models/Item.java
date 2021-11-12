package com.recommender.recommenderapp.Domain.Models;


import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Item {

    private String id;
    private String title;
    private ItemTypes type;

    private List<Attribute> stringAttributes;
    private List<Attribute> intAttributes;
    private List<Attribute> floatAttributes;
    private List<Attribute> setAttributes;

    public Item(String id, String title, ItemTypes type, Map<String,String> strings, Map<String,List<String>> arrayAttributes) {
        this.id = id;
        this.title = title;
        this.type = type;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        if (title.equals(null)){
            return "NoTitle";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemTypes getType() {
        return type;
    }

    public void setType(ItemTypes type) {
        this.type = type;
    }
}
