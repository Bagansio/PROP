package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Recommendation;

import java.util.Map;

public class CtrlRecommendations {
    private Map<String, Recommendation> recommendations;
    private Boolean useTemp;
    private CtrlDataFactory ctrlDataFactory;
    private static CtrlRecommendations _instance = new CtrlRecommendations();

    private CtrlRecommendations(){
        ctrlDataFactory = CtrlDataFactory.getInstance();
        useTemp = false;
    }

    public static CtrlRecommendations getInstance(){
        return _instance;
    }

    public void loadRecommendations(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
    }
}
