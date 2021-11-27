package com.recommender.recommenderapp.Domain.Models;


import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author  Melanie
 */
public class Item {

    private String id;
    private String title;
    private ItemTypes type;

    private Map<String, String> stringAttributes;
    private Map<String, Integer> intAttributes;
    private Map<String, Double> doubleAttributes;
    private Map<String, Set<String>> setAttributes;

    /**
     *
     * @param id -> identifier that will be assigned to the newly created item
     * @param title -> title that will be assigned to the item
     * @param type -> type that will be assigned to the item
     */
    public Item(String id, String title, ItemTypes type) {
        this.id = id;
        this.title = title;
        this.type = type;

        stringAttributes = new HashMap<>();
        intAttributes = new HashMap<>();
        doubleAttributes = new HashMap<>();
        setAttributes = new HashMap<>();
    }

    /**
     * creates an empty Item and initializes its data structures
     */
    public Item() {
        stringAttributes = new HashMap<>();
        intAttributes = new HashMap<>();
        doubleAttributes = new HashMap<>();
        setAttributes = new HashMap<>();
    }

    /**
     *
     * @param attributeName -> name of the integer attribute
     * @param value -> value of the attribute
     */
    public void addIntAttribute(String attributeName, Integer value){
        intAttributes.put(attributeName,value);
    }

    /**
     *
     * @param attributeName -> name of the double attribute
     * @param value -> value of the attribute
     */
    public void addDoubleAttribute(String attributeName, Double value){
        doubleAttributes.put(attributeName,value);
    }

    /**
     *
     * @param attributeName -> name of the set attribute
     * @param value -> set that will be added
     */
    public void addSetAttribute(String attributeName, Set<String> value){
        setAttributes.put(attributeName,value);
    }

    /**
     *
     * @param attributeName -> name of the String attribute
     * @param value -> value of the String attribute
     */
    public void addStringAttribute(String attributeName, String value){
        stringAttributes.put(attributeName,value);
    }

    /**
     *
     * @return all the String attributes of the item
     */
    public Map<String, String> getStringAttributes() {
        return stringAttributes;
    }

    /**
     *
     * @param stringAttributes -> set of Strings that will be added to the Item
     */
    public void setStringAttributes(Map<String, String> stringAttributes) {
        this.stringAttributes = stringAttributes;
    }

    /**
     *
     * @return all the integer attributes of the item
     */
    public Map<String, Integer> getIntAttributes() {
        return intAttributes;
    }

    /**
     *
     * @param intAttributes -> set of integer attributes that will be added to the item
     */
    public void setIntAttributes(Map<String, Integer> intAttributes) {
        this.intAttributes = intAttributes;
    }

    /**
     *
     * @return all the double attributes of the item
     */
    public Map<String, Double> getDoubleAttributes() {
        return doubleAttributes;
    }

    /**
     *
     * @param doubleAttributes -> set of double attributes that will be added to the item
     */
    public void setDoubleAttributes(Map<String, Double> doubleAttributes) {
        this.doubleAttributes = doubleAttributes;
    }

    /**
     *
     * @return all the set attributes of the item
     */
    public Map<String, Set<String>> getSetAttributes() {
        return setAttributes;
    }

    /**
     *
     * @param setAttributes -> specifies the sets that will be assigned to the item
     */
    public void setSetAttributes(Map<String, Set<String>> setAttributes) {
        this.setAttributes = setAttributes;
    }

    /**
     *
     * @return the identifier of the item
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id -> identifier that will be assigned to the item
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the title of the item
     */
    public String getTitle() {
        if (title.equals(null)) {
            return "NoTitle";
        }
        return title;
    }

    /**
     *
     * @param title -> title that will be assigned to the item
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return type of the item
     */
    public ItemTypes getType() {
        return type;
    }

    /**
     *
     * @param type ->type that will be assigned to the item
     */
    public void setType(ItemTypes type) {
        this.type = type;
    }
}
