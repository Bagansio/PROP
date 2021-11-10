package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Controllers.*;


public class CtrlDataFactory {

    private static CtrlDataFactory _instance = null;

    public static CtrlDataFactory Intance(){
        if(_instance == null){
            _instance = new CtrlDataFactory();
        }
        return _instance;
    }

    public ICtrlItem getCtrlItem(){
        return new CtrlItem();
    }

    public ICtrlUser getCtrlUser(){
        return new CtrlUser();
    }
}
