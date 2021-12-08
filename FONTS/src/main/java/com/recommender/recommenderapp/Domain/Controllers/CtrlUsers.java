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
    private Boolean useTemp = false;
    private CtrlDataFactory ctrlDataFactory = CtrlDataFactory.getInstance();
    private Map<String, User> users;
    private Map<String, User> knownUsers;
    private Map<String, User> unknownUsers;

    private static CtrlUsers _instance = new CtrlUsers();


    private CtrlUsers(){

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
    private void loadUsers(){
        ICtrlData ctrlData = ctrlDataFactory.getICtrlData();

        Map<String, Item> items = CtrlItems.getInstance().getItems();
        users = ctrlData.loadUsers(dataset,useTemp,items);
        knownUsers = ctrlData.loadKnownUsers(dataset,useTemp,items);
        unknownUsers = ctrlData.loadUnknownUsers(dataset,useTemp,items);
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

}
