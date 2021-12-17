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


    /**
     *
     * @param itemTitle
     * @return a Matrix of recommendations data, each row contains a recommendation
     *         each column contains -> 0 recommendationID
     *                              -> 1 AlgorithmType
     *                              -> 2 PrecisionType
     *                              -> 3 Rate/Score
     *                              -> next items
     */
    public String[][]  searchRecommendationsOfCurrentUser(String itemTitle){
        String currentUser = CtrlUsers.getInstance().getCurrentUserId();
        List<String[]> search = new ArrayList<>();
        for(Recommendation recommendation : recommendations.values()){
            if(recommendation.getUser().getId().equals(currentUser) && searchRatings(recommendation,itemTitle)) {
                List<String> recommendationData = new ArrayList<>();

                recommendationData.add(recommendation.getId());
                recommendationData.add(recommendation.getAlgorithmType());
                recommendationData.add(recommendation.getPrecisionType());
                recommendationData.add(String.valueOf(recommendation.getScore()));
                Map<String,Double> items = recommendation.getRecommendedItems();
                for(String itemId : items.keySet()){
                    recommendationData.add(CtrlItems.getInstance().getItems().get(itemId).getTitle() + "-" + String.format("%.3f",items.get(itemId)));
                }
                search.add(recommendationData.toArray(new String[0]));
            }
        }

        return search.toArray(new String[0][]);
    }
    public boolean searchRatings(Recommendation recommendation, String itemTitle){
        System.out.println(recommendation.getRecommendedItems().size());
        for(String item : recommendation.getRecommendedItems().keySet()){
            System.out.println(CtrlItems.getInstance().getItems().get(item).getTitle());
            if(CtrlItems.getInstance().getItems().get(item).getTitle().contains(itemTitle)) {
                return true;
            }
        }
        return false;
    }
}
