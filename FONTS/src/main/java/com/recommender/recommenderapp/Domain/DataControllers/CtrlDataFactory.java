package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Controllers.CtrlCSVItemList;
import com.recommender.recommenderapp.Data.Controllers.CtrlCSVUsers;


public class CtrlDataFactory {

    private static CtrlDataFactory _instance = null;

    public static CtrlDataFactory Intance(){
        if(_instance == null){
            _instance = new CtrlDataFactory();
        }
        return _instance;
    }

    public ICtrlCSVItemList getICtrlCSVItemList(){
        return new CtrlCSVItemList();
    }

    public ICtrlCSVUser getICtrlCSVUser(){
        return new CtrlCSVUsers();
    }
}
