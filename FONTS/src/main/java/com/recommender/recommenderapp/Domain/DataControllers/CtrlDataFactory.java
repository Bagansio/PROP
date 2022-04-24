package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Controllers.CtrlData;


/**
 * A Singleton Data Controller Factory
 * @author Alex
 */
public class CtrlDataFactory {

    private static CtrlDataFactory _instance = new CtrlDataFactory();


    private CtrlDataFactory(){

    }

    /**
     * @return the Instance of the class itself
     */
    public static CtrlDataFactory getInstance() { return _instance;}


    public ICtrlData getICtrlData() {
        return new CtrlData();
    }
}