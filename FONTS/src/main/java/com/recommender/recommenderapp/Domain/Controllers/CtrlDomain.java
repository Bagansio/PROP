package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.Map;

public class CtrlDomain {

    private String[] datasets;

    private final CtrlDataFactory ctrlDataFactory = CtrlDataFactory.getInstance();
    private CtrlItems ctrlItems = CtrlItems.getInstance();
    private CtrlUsers ctrlUsers = CtrlUsers.getInstance();



    public CtrlDomain() {
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        this.datasets = ctrlData.getDatasets();
    }


    public String[] getDatasets(){
        return datasets;
    }


    public void setDataset(String dataset){
        ctrlItems.setDataset(dataset);
        ctrlUsers.setDataset(dataset);
    }

    public boolean loadItems(){
        return ctrlItems.loadItems();
    }

    public boolean loadUsers(){
        return ctrlUsers.loadUsers();
    }

    public boolean loadData(String dataset){
        setDataset(dataset);
        return loadUsers() && loadItems();
    }

    public Map<String, Item> getItems(){
        return ctrlItems.getItems();
    }
}
