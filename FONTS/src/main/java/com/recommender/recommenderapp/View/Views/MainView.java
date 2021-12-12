package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainView {

    @FXML
    public ChoiceBox<String>  algorithmCheckBox;

    @FXML
    public Button recommendButton;

    @FXML
    public Button addRatesButton;

    @FXML
    public Button editRatesButton;

    @FXML
    public Button exitButton;


    @FXML
    public Button profileButton;

    /**
            * Creates an instance of MainView
     */
    public MainView(){
        System.out.println("Using Main VIEW");
    }

    /**
     * @brief handles the empty datasets Exception
     */
    private void emptyAlgorithmsException(){
        Views.buildErrorAlert("Error loading the algorithms").showAndWait();
        Platform.exit();
        System.exit(0);
    }

    private void loadAlgorithms(){
        String[] algorithms = CtrlView.ctrlDomain.getAlgorithms();
        if(algorithms.length == 0) emptyAlgorithmsException();
        algorithmCheckBox.getItems().addAll(algorithms);
        algorithmCheckBox.setValue(algorithms[0]);
    }

    public void initialize() {
        loadAlgorithms();

        try {
            addRatesButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\addSmall.png").toExternalForm())));
            editRatesButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\editSmall.png").toExternalForm())));
            exitButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\exit.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\user.png").toExternalForm())));
        }
        catch(Exception e)
        {

        }

    }
}
