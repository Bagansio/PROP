package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.*;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CtrlRecommendations {
    private String dataset;
    private Map<String, Recommendation> recommendations;
    private CtrlDataFactory ctrlDataFactory;
    private static CtrlRecommendations _instance = new CtrlRecommendations();

    private CtrlRecommendations(){
        recommendations = new LinkedHashMap<>();
        ctrlDataFactory = CtrlDataFactory.getInstance();
    }

    public void addRecommendation(Recommendation recommendation){
        recommendations.put(recommendation.getId(),recommendation);
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
        recommendations = null;
    }

    public static CtrlRecommendations getInstance(){
        return _instance;
    }

    public void initializeRecommendations(){
        recommendations = new LinkedHashMap<>();
    }

    public String getNewId(){
        String newId = "1";
        if(recommendations.size() > 0){
            List<String> recommendationsIds = new ArrayList<String>(recommendations.keySet());
            newId = String.valueOf(Long.parseLong(recommendationsIds.get(recommendationsIds.size()-1)) + 1);
        }
        return  newId;
    }

    public boolean loadRecommendations(){
        boolean loadedCorrectly = true;
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        recommendations = ctrlData.loadRecommendations(dataset, CtrlUsers.getInstance().getKnownUsers());
        if(recommendations == null){
            initializeRecommendations();
            loadedCorrectly = false;
        }
        return loadedCorrectly;
    }

    public Map<String,Recommendation> getRecommendations(){
        if(recommendations == null){
            loadRecommendations();
        }
        return  recommendations;
    }

    public boolean saveRecommendations(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.writeRecommendations(dataset,recommendations.values().toArray(new Recommendation[0]));
    }

    public boolean saveNewRecommendation(Recommendation recommendation){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.writeNewRecommendations(dataset,recommendation);
    }

}
