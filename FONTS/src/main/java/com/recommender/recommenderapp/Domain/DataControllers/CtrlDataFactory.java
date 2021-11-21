package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Controllers.CtrlCSVItemList;
import com.recommender.recommenderapp.Data.Controllers.CtrlCSVUsers;


/**
 * A SingleTon Data Controler Factory
 * @author Alex
 */
public class CtrlDataFactory {

    private static CtrlDataFactory _instance = null;

    /**
     *
     * @return the Instance of the class itself
     */
    public static CtrlDataFactory Intance(){
        if(_instance == null){
            _instance = new CtrlDataFactory();
        }
        return _instance;
    }

    /**
     *
     * @return Instance of CtrlCSVItemList
     */
    public ICtrlCSVItemList getICtrlCSVItemList(){
        return new CtrlCSVItemList();
    }


    /**
     *
     * @return Instance of CtrlCSVser
     */
    public ICtrlCSVUser getICtrlCSVUser(){
        return new CtrlCSVUsers();
    }
}
