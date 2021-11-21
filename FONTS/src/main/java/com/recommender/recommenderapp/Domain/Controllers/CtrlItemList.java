package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Data.Utils.Datasets;
import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVItemList;
import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.Map;

/**
 * @author Alex
 *
 * Its a Singleton
 */
public class CtrlItemList {
    private Datasets dataset = Datasets.movies;
    private final CtrlDataFactory  ctrlDataFactory = new CtrlDataFactory();
    private Map<String,Item> itemList;
    private static CtrlItemList _instance = null;

    public static CtrlItemList Instance(){
        if(_instance == null){
            _instance = new CtrlItemList();
        }
        return _instance;
    }

    public void setItemList(Map<String,Item> itemList){
        this.itemList = itemList;
    }

    public void setDataset(Datasets dataset) {
        this.dataset = dataset;
    }

    public Map<String,Item> getItemList(){
        if(itemList == null){
            loadItemList();
        }
        return itemList;
    }

    private void loadItemList(){
        ICtrlCSVItemList loader = ctrlDataFactory.getICtrlCSVItemList();
        itemList = loader.getItemList(dataset);
    }
}
