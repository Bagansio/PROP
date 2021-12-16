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
    private final CtrlDataFactory  ctrlDataFactory;
    private Map<String,Item> items;
    private static CtrlItems _instance = new CtrlItems();

    private CtrlItems(){
        ctrlDataFactory = CtrlDataFactory.getInstance();
    }

    public static CtrlItems getInstance(){
        return _instance;
    }

    public void setItems(Map<String,Item> items){
        this.items = items;
    }

    public void setDataset(String dataset) {
        System.out.println(ctrlDataFactory.getICtrlData().tempDatasetToNormal(dataset));
        this.dataset = ctrlDataFactory.getICtrlData().tempDatasetToNormal(dataset);
        items = null;
    }

    public String[] getDatasets(){
        return ctrlDataFactory.getICtrlData().getDatasets();
    }

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
