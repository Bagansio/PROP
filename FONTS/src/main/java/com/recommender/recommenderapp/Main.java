package com.recommender.recommenderapp;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import javafx.application.Application;


/**
 * @brief Main Class, where launch the app.
 * @author Alex
 */
public class Main{



    /**
     * @brief Main method. Launches the app
     */
    public static void main(String[] args) {

        Application.launch(CtrlView.class,args);
    }
}
