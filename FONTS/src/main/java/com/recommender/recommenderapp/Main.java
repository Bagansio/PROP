package com.recommender.recommenderapp;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(Paths.get(System.getProperty("user.dir") + "\\resources\\templates\\MainView.fxml").toUri().toURL());
        primaryStage.setTitle("OTHELLO");
        primaryStage.setScene(new Scene(root, 1464, 824));
        primaryStage.show();
    }

    /**
     * @brief Main method.
     */
    public static void main(String[] args) {
        //ViewCtrl.domainCtrl = new DomainCtrl();
        launch();
    }
}
