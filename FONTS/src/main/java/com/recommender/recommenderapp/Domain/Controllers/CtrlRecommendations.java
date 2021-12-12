package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Recommendation;

import java.util.LinkedHashMap;
import java.util.Map;

public class CtrlRecommendations {
    private String dataset;
    private Map<String, Recommendation> recommendations;
    private Boolean useTemp;
    private CtrlDataFactory ctrlDataFactory;
    private static CtrlRecommendations _instance = new CtrlRecommendations();

    private CtrlRecommendations(){
        recommendations = new LinkedHashMap<>();
        ctrlDataFactory = CtrlDataFactory.getInstance();
        useTemp = false;
    }

    public void addRecommendation(Recommendation recommendation){
        recommendations.put(recommendation.getId(),recommendation);
    }


    public static CtrlRecommendations getInstance(){
        return _instance;
    }

    public void loadRecommendations(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
    }

    public boolean saveRecommendations(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.writeRecommendations("movies", useTemp,recommendations.values().toArray(new Recommendation[0]));
    }
}
