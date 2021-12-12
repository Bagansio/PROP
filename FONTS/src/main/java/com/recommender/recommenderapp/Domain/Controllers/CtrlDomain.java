package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class CtrlDomain {

    private String[] datasets;
    private Boolean useTemp;
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
        return ctrlAlgorithms.getAlgorithms();
    }

    public String[] getPrecisions(){
        return ctrlAlgorithms.getPrecisions();
    }

    public boolean setCurrentUser(String userId){
        return ctrlUsers.setCurrentUser(userId);
    }

    public void loadAlgorithms(){
        ctrlAlgorithms.preprocessingData(ctrlItems.getItems(),ctrlUsers.getUsers());
    }

    public String recommend(String algorithm, String precision){
        Recommendation recommendation = ctrlAlgorithms.recommend(algorithm, precision, ctrlUsers.getKnownCurrentUser(),ctrlUsers.getUnknownItemsFromCurrentUser());
        // save recommendation
        String itemId = recommendation.getRecommendedItems().entrySet().iterator().next().getKey();
        return ctrlItems.getItems().get(itemId).getTitle();
    }
}
