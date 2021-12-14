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
    private boolean useTemp;
    private String dataset;
    private final CtrlDataFactory  ctrlDataFactory;
    private Map<String,Item> items;
    private static CtrlItems _instance = new CtrlItems();

    private CtrlItems(){
        ctrlDataFactory = CtrlDataFactory.getInstance();
        useTemp = false;
    }

    public static CtrlItems getInstance(){
        return _instance;
    }

    public void setItems(Map<String,Item> items){
        this.items = items;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
        items = null;
    }
/*
    public void setUseTemp(boolean useTemp){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        if(useTemp && ctrlData.existTemp(dataset)){
            this.useTemp = true;
        }
        else this.useTemp = false;
        return this.useTemp;
    }
*/
    public Map<String,Item> getItems(){
        if(items == null){
            loadItems();
        }
        return items;
    }

    public boolean loadItems(){
        ICtrlData loader = ctrlDataFactory.getICtrlData();
        items = loader.loadItems(dataset);
        return items != null;
    }
}
