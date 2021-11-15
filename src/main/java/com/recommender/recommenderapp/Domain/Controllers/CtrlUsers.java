package com.recommender.recommenderapp.Domain.Controllers;

import com.recommender.recommenderapp.Domain.DataControllers.CtrlDataFactory;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVUser;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;

public class CtrlUsers {
    private CtrlDataFactory ctrlDataFactory = new CtrlDataFactory();
    private Map<String, User> users;
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

    private void loadUsers(){
        ICtrlCSVUser ctrlCSVUser = ctrlDataFactory.getICtrlCSVUser();
        CtrlItemList ctrlItemList = new CtrlItemList();
        users = ctrlCSVUser.loadUserRatings(ctrlItemList.getItemList());
    }

}
