package com.recommender.recommenderapp.Domain.Models;


import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Item {

    private String id;
    private String title;
    private ItemTypes type;

    private Map<String, String> stringAttributes;
    private Map<String, Integer> intAttributes;
    private Map<String, Float> floatAttributes;
    private Map<String, Set<String>> setAttributes;

    public Item(String id, String title, ItemTypes type, Map<String, String> strings, Map<String, List<String>> arrayAttributes) {
        this.id = id;
        this.title = title;
        this.type = type;

    }

    public Map<String, String> getStringAttributes() {
        return stringAttributes;
    }

    public void setStringAttributes(Map<String, String> stringAttributes) {
        this.stringAttributes = stringAttributes;
    }

    public Map<String, Integer> getIntAttributes() {
        return intAttributes;
    }

    public void setIntAttributes(Map<String, Integer> intAttributes) {
        this.intAttributes = intAttributes;
    }

    public Map<String, Float> getFloatAttributes() {
        return floatAttributes;
    }

    public void setFloatAttributes(Map<String, Float> floatAttributes) {
        this.floatAttributes = floatAttributes;
    }

    public Map<String, Set<String>> getSetAttributes() {
        return setAttributes;
    }

    public void setSetAttributes(Map<String, Set<String>> setAttributes) {
        this.setAttributes = setAttributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        if (title.equals(null)) {
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
