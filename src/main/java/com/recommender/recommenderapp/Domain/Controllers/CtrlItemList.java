package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.ItemList;

/**
 * @author Alex
 *
 * Its a Singleton
 */
public class CtrlItemList {
    private ItemList itemList;
    private static CtrlItemList _instance = null;

    public static CtrlItemList Instance(){
        if(_instance == null){
            _instance = new CtrlItemList();
        }
        return _instance;
    }

    public void setItemList(ItemList itemList){
        this.itemList = itemList;
    }

    public ItemList getItemList(){
        return itemList;
    }

    public Item getItem(String itemId){
        return itemList.getItem(itemId);
    }
}
