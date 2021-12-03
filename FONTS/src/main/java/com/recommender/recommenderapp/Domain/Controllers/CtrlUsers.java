package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Data.Controllers.CtrlData;
import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;


/**
 * @author  Alex
 */
public class CtrlUsers {
    private String dataset;
    private Boolean useTemp = false;
    private CtrlDataFactory ctrlDataFactory = new CtrlDataFactory();
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

    public boolean setUseTemp(boolean useTemp){
        CtrlData ctrlData = ctrlDataFactory.getCtrlData();
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
    }


    /**
     * Load the users using the ctrlCSVUser
     */
    private void loadUsers(){
        CtrlData ctrlData = ctrlDataFactory.getCtrlData();

        Map<String, Item> items = CtrlItems.getInstance().getItems();
        users = ctrlData.getUsers(dataset,useTemp,items);
        knownUsers = ctrlData.getKnownUsers(dataset,useTemp,items);
        unknownUsers = ctrlData.getUnknownUsers(dataset,useTemp,items);
    }

}
