package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Controllers.CtrlData;


/**
 * A SingleTon Data Controler Factory
 * @author Alex
 */
public class CtrlDataFactory {

    private static CtrlDataFactory _instance = null;


    public CtrlData getCtrlData() {
        return new CtrlData();
    }

    /**
     * @return the Instance of the class itself
     */
    public static CtrlDataFactory Intance() {
        if (_instance == null) {
            _instance = new CtrlDataFactory();
        }
        return _instance;
    }

}