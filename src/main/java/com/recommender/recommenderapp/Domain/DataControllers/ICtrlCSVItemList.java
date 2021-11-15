package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.Map;

public interface ICtrlCSVItemList {

    /**
     * @return  A Map (key = ItemId, value Item Itself) of items
     */
    public Map<String, Item> getItemList();

}
