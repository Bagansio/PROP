package com.recommender.recommenderapp.Domain.Controllers;

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
    private CtrlDataFactory ctrlDataFactory;
    private Map<String, User> users;
    private Map<String, User> knownUsers;
    private Map<String, User> unknownUsers;
    private User[] currentUser;

    private static CtrlUsers _instance = new CtrlUsers();


    private CtrlUsers(){
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
    }


    /**
     * Load the users using the Data controller
     */
    public boolean loadUsers(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();

        Map<String, Item> items = CtrlItems.getInstance().getItems();
        users = ctrlData.loadUsers(dataset,items);
        knownUsers = ctrlData.loadKnownUsers(dataset,items);
        unknownUsers = ctrlData.loadUnknownUsers(dataset,items);
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
        return knownUsers.containsKey(userId) || unknownUsers.containsKey(userId);
    }

    public boolean setCurrentUser(String userId) {
        boolean canSet = true;
        if(existsUser(userId)){
            this.currentUser[0] = knownUsers.get(userId);
            this.currentUser[1] = unknownUsers.get(userId);
            if(this.currentUser[0] == null)
                this.currentUser[0] = new User(userId);
            if(this.currentUser[1] == null)
                this.currentUser[1] = new User(userId);
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


    public String[][] searchRatingsOfCurrentUser(String itemId,int pos){
        Map<String,Double> searchResult = currentUser[pos].searchRatings(itemId);
        String[][] search = new String[searchResult.size()][3];

        int i = 0;
        for(String item : searchResult.keySet()){
            search[i] = new String[]{currentUser[pos].getItem(item).getTitle(),item,String.valueOf(searchResult.get(item))};
            ++i;
        }
        return search;
    }


    public void deleteRateOfCurrentUser(String itemId, int pos){

        currentUser[pos].deleteRate(itemId);
    }

    public void editRateOfCurrentUser(String itemId, Double newRate, int pos){
        currentUser[pos].rateItem(itemId,newRate);
    }

    public boolean saveUsersByBoolean(boolean isKnown){
        if(ctrlDataFactory.getICtrlData().existTemp(dataset)) {
            if (isKnown)
                return saveKnownUsers();
            else
                return saveUnknownUsers();
        }
        else{
            saveAll();
            CtrlRecommendations.getInstance().saveRecommendations();
        }
        return true;
    }

    public String getTempDataset(){
        return ctrlDataFactory.getICtrlData().getTempDataset(dataset);
    }

    public String getCurrentUserId(){
        return currentUser[0].getId();
    }


    /**
     *
     * @param userId
     * @return True  -> if doesn't exists a previous user with that id
     *         False -> if not
     */
    public boolean createUser(String userId){
        if(existsUser(userId))
            return false;
        User newUser = new User(userId);
        knownUsers.put(userId,newUser);
        newUser = new User(userId);
        unknownUsers.put(userId,newUser);
        setCurrentUser(userId);
        return true;
    }

    public boolean currentUserHasRatedItems(){
        return currentUser[0].getRatings().size() > 0 && currentUser[1].getRatings().size() > 0;
    }

    public void editCurrentUserId(String newUserId){
        String oldUserId = currentUser[0].getId();
        currentUser[0].setId(newUserId);
        currentUser[1].setId(newUserId);

        knownUsers.remove(oldUserId);
        unknownUsers.remove(oldUserId);
        knownUsers.put(newUserId,currentUser[0]);
        unknownUsers.put(newUserId,currentUser[1]);
    }


    public User[] getCurrentUser(){
        return currentUser;
    }

    public void addRateCurrent(Item item, Double rate, int pos){
        currentUser[pos].addItem(item);
        currentUser[pos].addRate(item.getId(),rate);
    }
}
