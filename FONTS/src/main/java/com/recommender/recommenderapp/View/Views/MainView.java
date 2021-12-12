package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

public class MainView {

    @FXML
    public ChoiceBox<String>  algorithmCheckBox;

    @FXML
    public ChoiceBox<String> precisionCheckBox;

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
     * @brief Creates an Alert that show the best item of a recommendation
     * @param recommendedItem
     */
    private void recommendAlert(String recommendedItem){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RECOMMENDATION");
        alert.setHeaderText("WE RECOMMEND YOU:");
        alert.setContentText(recommendedItem);
        try {
            alert.getDialogPane().setGraphic(new ImageView(new Image(Views.getPath("icons\\iconBig.png").toExternalForm())));
        }
        catch(Exception e){
            //nothing not needed
        }
        ButtonType ok = new ButtonType("OK");
        ButtonType goRecommendations = new ButtonType("SEE MORE");
        alert.getButtonTypes().setAll(ok,goRecommendations);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == goRecommendations){
            //change scene to recommendations
        }
    }


    /**
     * @brief Recommend the user the better items based in their known items
     * @param event
     */
    @FXML
    public void recommend(ActionEvent event){
        String recommendedItem = CtrlView.ctrlDomain.recommend(algorithmCheckBox.getValue(),precisionCheckBox.getValue());
        recommendAlert(recommendedItem);
    }

    /**
     * @brief handles the empty datasets Exception
     */
    private void emptyCheckBoxException(String errorName){
        Views.buildErrorAlert("Error loading the "+ errorName).showAndWait();
        Platform.exit();
        System.exit(0);
    }

    private void loadAlgorithms(){
        CtrlView.ctrlDomain.loadAlgorithms();
        String[] algorithms = CtrlView.ctrlDomain.getAlgorithms();
        if(algorithms.length == 0) emptyCheckBoxException("Algorithms");
        algorithmCheckBox.getItems().addAll(algorithms);
        algorithmCheckBox.setValue(algorithms[0]);
    }

    private void loadPrecisions(){
        String[] precisions = CtrlView.ctrlDomain.getPrecisions();
        if(precisions.length == 0) emptyCheckBoxException("Precisions");
        precisionCheckBox.getItems().addAll(precisions);
        precisionCheckBox.setValue(precisions[0]);
    }

    public void initialize() {
        System.out.println("Set current user:"+ CtrlView.ctrlDomain.setCurrentUser("53968"));

        loadAlgorithms();
        loadPrecisions();

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
