package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.*;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.*;
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
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        recommendations = ctrlData.loadRecommendations(dataset, CtrlUsers.getInstance().getKnownUsers());
        return true;
    }

    public Map<String,Recommendation> getRecommendations(){
        if(recommendations == null){
            loadRecommendations();
        }
        return  recommendations;
    }

    public void deleteRecommendation(String recommendationId){
        recommendations.remove(recommendationId);
    }

    public void editScoreRecommendation(String recommendationId,int newScore){
        recommendations.get(recommendationId).setScore(newScore);
    }

    public boolean saveRecommendations(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.writeRecommendations(dataset,recommendations.values().toArray(new Recommendation[0]));
    }

    public boolean saveNewRecommendation(Recommendation recommendation){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.writeNewRecommendations(dataset,recommendation);
    }

    public Map<String,String>[]  searchRecommendationsOfCurrentUser(String itemTitle){
        String currentUser = CtrlUsers.getInstance().getCurrentUserId();




        List<Map<String,String>>  search = new ArrayList<>();

        int i = 0;
        for(Recommendation recommendation : recommendations.values()){
            if(recommendation.getUser().getId() == currentUser && recommendation.searchRatings(itemTitle)) {
                Map<String,String> recommendationData = new HashMap<>();
                recommendationData.put("id",recommendation.getId());
                recommendationData.put("itemId")
                search.add()
            }
        }
        return search;
    }

}
