package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVUser;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;

public class CtrlUsers {
    private CtrlDataFactory ctrlDataFactory = new CtrlDataFactory();
    private Map<String, User> users;
    private Map<String, User> knownUsers;
    private Map<String, User> unknownUsers;

    private static CtrlUsers _instance = null;

    public static CtrlUsers Instance(){
        if(_instance == null){
            _instance = new CtrlUsers();
        }
        return _instance;
    }

    public Map<String, User> getUsers(){
        if (users == null){
            loadUsers();
        }
        return users;
    }

    public Map<String, User> getKnownUsers(){
        if (users == null){
            loadUsers();
        }
        return knownUsers;
    }

    public Map<String, User> getUnknownUsers(){
        if (users == null){
            loadUsers();
        }
        return unknownUsers;
    }

    private void loadUsers(){
        ICtrlCSVUser ctrlCSVUser = ctrlDataFactory.getICtrlCSVUser();
        CtrlItemList ctrlItemList = new CtrlItemList();
        Map<String, Item> items = ctrlItemList.getItemList();
        users = ctrlCSVUser.loadUserRatings(items);
        knownUsers = ctrlCSVUser.loadUserKnownRatings(items);
        unknownUsers = ctrlCSVUser.loadUserUnknownRatings(items);
    }

}
