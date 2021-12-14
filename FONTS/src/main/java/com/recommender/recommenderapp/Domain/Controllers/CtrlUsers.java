package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Data.Controllers.CtrlData;
import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;


/**
 * @author  Alex
 */
public class CtrlUsers {
    private String dataset;
    private Boolean useTemp;
    private CtrlDataFactory ctrlDataFactory;
    private Map<String, User> users;
    private Map<String, User> knownUsers;
    private Map<String, User> unknownUsers;
    private User[] currentUser;

    private static CtrlUsers _instance = new CtrlUsers();


    private CtrlUsers(){
        useTemp = false;
        ctrlDataFactory = CtrlDataFactory.getInstance();
        currentUser = new User[2];
    }

    /**
     *
     * @return the Instance of the class itself
     */
    public static CtrlUsers getInstance(){
        return _instance;
    }


    /**
     *
     * @return users if its null load all the users first
     */
    public Map<String, User> getUsers(){
        if (users == null){
            loadUsers();
        }
        return users;
    }


    /**
     *
     * @return knownUser if its null load all the users first
     */
    public Map<String, User> getKnownUsers(){
        if (knownUsers == null){
            loadUsers();
        }
        return knownUsers;
    }

    /**
     *
     * @param useTemp new condition to use temp files
     * @return the condition value after try to change it
     */
    public boolean setUseTemp(boolean useTemp){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        if(useTemp && ctrlData.existTemp(dataset)){
            this.useTemp = true;
        }
        else this.useTemp = false;
        return this.useTemp;
    }

    /**
     *
     * @return unknownUsers if its null load all the users first
     */
    public Map<String, User> getUnknownUsers() {
        if (unknownUsers == null) {
            loadUsers();
        }
        return unknownUsers;
    }


    /**
     *
     * @param dataset dataset to load the users
     */
    public void setDataset(String dataset) {

        this.dataset = dataset;
        users = null;
        unknownUsers = null;
        knownUsers = null;
    }


    /**
     * Load the users using the Data controller
     */
    public boolean loadUsers(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();

        Map<String, Item> items = CtrlItems.getInstance().getItems();
        users = ctrlData.loadUsers(dataset,useTemp,items);
        knownUsers = ctrlData.loadKnownUsers(dataset,useTemp,items);
        unknownUsers = ctrlData.loadUnknownUsers(dataset,useTemp,items);
        return users != null && knownUsers != null && unknownUsers != null;
    }


    /**
     *
     * @return TRUE if correctly save users, FALSE if not
     */
    public boolean saveUsers(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.saveUsers(dataset,users.values().toArray(new User[0]));
    }

    /**
     *
     * @return TRUE if correctly save known users, FALSE if not
     */
    public boolean saveKnownUsers(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.saveKnownUsers(dataset,knownUsers.values().toArray(new User[0]));
    }


    /**
     *
     * @return TRUE if correctly save unknown users, FALSE if not
     */
    public boolean saveUnknownUsers(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();
        return ctrlData.saveUnknownUsers(dataset,unknownUsers.values().toArray(new User[0]));
    }

    /**
     *
     * @return TRUE if correctly save all users in dataset temp, FALSE if not
     */
    public boolean saveAll(){
        return saveUsers() && saveKnownUsers() && saveUnknownUsers();
    }


    public boolean existsUser(String userId){
        return knownUsers.containsKey(userId);
    }

    public boolean setCurrentUser(String userId) {
        boolean canSet = true;
        if(existsUser(userId)){
            this.currentUser[0] = knownUsers.get(userId);
            this.currentUser[1] = unknownUsers.get(userId);
        }
        else
            canSet = false;
        return canSet;
    }

    public User getKnownCurrentUser(){
        return currentUser[0];
    }

    public Map<String,Item> getUnknownItemsFromCurrentUser(){
        return currentUser[1].getItems();
    }


    public String[][] searchRatingsOfCurrentUser(String itemId){
        Map<String,Double> searchResult = currentUser[0].searchRatings(itemId);
        String[][] search = new String[searchResult.size()][3];

        int i = 0;
        for(String item : searchResult.keySet()){
            search[i] = new String[]{currentUser[0].getItem(item).getTitle(),item,String.valueOf(searchResult.get(item))};
            ++i;
        }
        return search;
    }


    public void deleteRateOfCurrentUser(String itemId){
        currentUser[0].deleteRate(itemId);
    }

    public void editRateOfCurrentUser(String itemId, Double newRate){
        currentUser[0].rateItem(itemId,newRate);
    }
}
