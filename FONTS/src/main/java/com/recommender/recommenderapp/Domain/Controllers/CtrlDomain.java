package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;


/**
 * @author  Alex
 */
public class CtrlDomain {
    private boolean useTemp;
    private CtrlItems ctrlItems = CtrlItems.getInstance();
    private CtrlUsers ctrlUsers = CtrlUsers.getInstance();
    private CtrlAlgorithms ctrlAlgorithms = CtrlAlgorithms.getInstance();
    private CtrlRecommendations ctrlRecommendations = CtrlRecommendations.getInstance();


    public CtrlDomain() {
    }


    public String[] getDatasets(){
        return ctrlItems.getDatasets();
    }


    public void setDataset(String dataset){
        ctrlItems.setDataset(dataset);
        ctrlUsers.setDataset(dataset);
        ctrlRecommendations.setDataset(dataset);
    }

    public boolean loadItems(){
        return ctrlItems.loadItems();
    }

    public boolean loadUsers(){
        return ctrlUsers.loadUsers();
    }

    public boolean loadRecommendations(){ return ctrlRecommendations.loadRecommendations();}

    public boolean loadData(String dataset){
        setDataset(dataset);
        return loadUsers() && loadItems() && loadRecommendations();
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
        ctrlRecommendations.addRecommendation(recommendation);
        ctrlRecommendations.saveNewRecommendation(recommendation);
        // save recommendation
        String itemId = recommendation.getRecommendedItems().entrySet().iterator().next().getKey();
        return ctrlItems.getItems().get(itemId).getTitle();
    }

    public boolean currentUserHasRatedItems(){
        return ctrlUsers.currentUserHasRatedItems();
    }

    public String[][] searchRatingsOfCurrentUser(String itemId,boolean isKnown){
        return ctrlUsers.searchRatingsOfCurrentUser(itemId,getPosCurrentUser(isKnown));
    }


    public void deleteRateOfCurrentUser(String itemId,boolean isKnown){
        ctrlUsers.deleteRateOfCurrentUser(itemId,getPosCurrentUser(isKnown));
        changeToTempDataset();
    }

    private int getPosCurrentUser(boolean isKnown) {
        int pos = 1;
        if(isKnown)
            pos = 0;
        return pos;
    }

    public void editRateOfCurrentUser(String itemId, Double newRate, boolean isKnown){
        ctrlUsers.editRateOfCurrentUser(itemId,newRate,getPosCurrentUser(isKnown));
        changeToTempDataset();
    }

    public boolean existsUser(String userId){
       return ctrlUsers.existsUser(userId);
    }

    public void changeToTempDataset(){
        useTemp = true;
        String tempDataset = ctrlUsers.getTempDataset();
        ctrlRecommendations.setDataset(tempDataset);
        ctrlUsers.setDataset(tempDataset);
        saveAll();
    }

    public void deleteRecommendation(String recommendationId){
        ctrlRecommendations.deleteRecommendation(recommendationId);
        ctrlRecommendations.saveRecommendations();
    }

    public void editScoreRecommendation(String recommendationId,int newScore){
        ctrlRecommendations.editScoreRecommendation(recommendationId,newScore);
        ctrlRecommendations.saveRecommendations();
    }


    public String[][] searchRecommendations(String itemTitle){
        return ctrlRecommendations.searchRecommendationsOfCurrentUser(itemTitle);
    }

    public String[] getRecommendationData(String recommendationId){
        return ctrlRecommendations.getRecommendationData(ctrlRecommendations.getRecommendation(recommendationId));
    }


    public boolean saveAll(){
        return ctrlUsers.saveAll() && ctrlRecommendations.saveRecommendations();
    }

    public boolean createUser(String userId){
        return ctrlUsers.createUser(userId);
    }

    public String getCurrentUserId(){
        return ctrlUsers.getCurrentUserId();
    }

    public void editCurrentUserId(String newUserId){
        ctrlUsers.editCurrentUserId(newUserId);
        changeToTempDataset();
    }


    public String[][] searchItems(String itemId, boolean isKnown){
        User[] currentUser = ctrlUsers.getCurrentUser();
        return ctrlItems.searchItems(itemId, currentUser);
    }

    public void addRateOfCurrentUser(String itemId, double newRate, boolean isKnown){
        ctrlUsers.addRateCurrent(ctrlItems.getItems().get(itemId), newRate, getPosCurrentUser(isKnown));
        changeToTempDataset();
    }
}
