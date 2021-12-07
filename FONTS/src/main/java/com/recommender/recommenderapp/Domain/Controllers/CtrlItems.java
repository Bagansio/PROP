package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.Map;

/**
 * @author Alex
 *
 * Its a Singleton
 */
public class CtrlItems {
    private String dataset;
    private final CtrlDataFactory  ctrlDataFactory = CtrlDataFactory.getInstance();
    private Map<String,Item> items;
    private static CtrlItems _instance = new CtrlItems();

    private CtrlItems(){
    }

    public static CtrlItems getInstance(){
        return _instance;
    }

    public void setItems(Map<String,Item> items){
        this.items = items;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public Map<String,Item> getItems(){
        if(items == null){
            loadItems();
        }
        return items;
    }

    private void loadItems(){
        ICtrlData loader = ctrlDataFactory.getICtrlData();
        items = loader.loadItems(dataset);
    }
}
