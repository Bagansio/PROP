package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class CtrlDomain {

    private String[] datasets;

    private final CtrlDataFactory ctrlDataFactory = CtrlDataFactory.getInstance();
    private CtrlItems ctrlItems = CtrlItems.getInstance();
    private CtrlUsers ctrlUsers = CtrlUsers.getInstance();
    private CtrlAlgorithms ctrlAlgorithms = CtrlAlgorithms.getInstance();


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

    public String[] getAlgorithms(){
        return Stream.of(AlgorithmTypes.values()).map(AlgorithmTypes::name).toArray(String[]::new);
    }

    public boolean setCurrentUser(String userId){
        return ctrlUsers.setCurrentUser(userId);
    }

    public void loadAlgorithms(){
        ctrlAlgorithms.preprocessingData(ctrlItems.getItems(),ctrlUsers.getUsers());
    }

    public void recommend(String algorithm){
        ctrlAlgorithms.recommend(algorithm, ctrlUsers.getKnownCurrentUser(),ctrlUsers.getUnknownItemsFromCurrentUser());
    }
}
