package com.recommender.recommenderapp;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main{



    /**
     * @brief Main method. Launches the app
     */
    public static void main(String[] args) {

        Application.launch(CtrlView.class,args);
    }
}
