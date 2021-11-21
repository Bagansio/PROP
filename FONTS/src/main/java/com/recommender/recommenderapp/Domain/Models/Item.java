package com.recommender.recommenderapp.Domain.Models;


import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Item {

    private String id;
    private String title;
    private ItemTypes type;

    private Map<String, String> stringAttributes;
    private Map<String, Integer> intAttributes;
    private Map<String, Double> doubleAttributes;
    private Map<String, Set<String>> setAttributes;

    public Item(String id, String title, ItemTypes type) {
        this.id = id;
        this.title = title;
        this.type = type;

        stringAttributes = new HashMap<>();
        intAttributes = new HashMap<>();
        doubleAttributes = new HashMap<>();
        setAttributes = new HashMap<>();
    }

    public Item() {
        stringAttributes = new HashMap<>();
        intAttributes = new HashMap<>();
        doubleAttributes = new HashMap<>();
        setAttributes = new HashMap<>();
    }

    public void addIntAttribute(String attributeName, Integer value){
        intAttributes.put(attributeName,value);
    }

    public void addDoubleAttribute(String attributeName, Double value){
        doubleAttributes.put(attributeName,value);
    }

    public void addSetAttribute(String attributeName, Set<String> value){
        setAttributes.put(attributeName,value);
    }

    public void addStringAttribute(String attributeName, String value){
        stringAttributes.put(attributeName,value);
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

    public Map<String, Double> getDoubleAttributes() {
        return doubleAttributes;
    }

    public void setDoubleAttributes(Map<String, Double> doubleAttributes) {
        this.doubleAttributes = doubleAttributes;
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
