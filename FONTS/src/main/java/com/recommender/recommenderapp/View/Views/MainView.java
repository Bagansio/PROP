package com.recommender.recommenderapp.View.Views;

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
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Optional;


/**
 * @author Melanie
 */
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

    @FXML
    public void exit(ActionEvent event){
        Optional<ButtonType> result = Views.buildAlert(Alert.AlertType.CONFIRMATION,"Are you sure to exit?","EXIT").showAndWait();
        if(result.get() == ButtonType.OK) {
            CtrlView.changeScene(event,"LoginUserView");
        }
    }

    @FXML
    public void goProfile(ActionEvent event){
        Views.changeToProfile(event);
    }

    /**
            * Creates an instance of MainView
     */
    public MainView(){

    }


    /**
     * @brief Creates an Alert that show the best item of a recommendation
     * @param recommendedItem
     */
    private void recommendAlert(ActionEvent event,String recommendedItem){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RECOMMENDATION");
        alert.setHeaderText("WE RECOMMEND YOU:");
        alert.setContentText(recommendedItem);
        try {
            alert.getDialogPane().setGraphic(new ImageView(new Image(Views.getPath("icons" + File.separator + "iconBig.png").toExternalForm())));
        }
        catch(Exception e){
            //nothing not needed
        }
        ButtonType ok = new ButtonType("OK");
        ButtonType goRecommendations = new ButtonType("SEE MORE");
        alert.getButtonTypes().setAll(ok,goRecommendations);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == goRecommendations){

            CtrlView.changeScene(event,"RecommendationsView");
        }
    }

    @FXML
    public void add(ActionEvent event) {CtrlView.changeScene(event,"ItemAddView");}

    @FXML
    public void changeEditRatesView(ActionEvent event){
        CtrlView.changeScene(event,"ItemRatesView");
    }

    /**
     * @brief Recommend the user the better items based in their known items
     * @param event
     */
    @FXML
    public void recommend(ActionEvent event){
        if(CtrlView.ctrlDomain.currentUserHasRatedItems()) {
            String recommendedItem = CtrlView.ctrlDomain.recommend(algorithmCheckBox.getValue(), precisionCheckBox.getValue());
            recommendAlert(event, recommendedItem);
        }
        else{
            Views.buildAlert(Alert.AlertType.ERROR,"YOU HAVE NOT ANY KNOWN RATES OR UNKNOWN RATES","ERROR").showAndWait();
        }
    }

    /**
     * @brief handles the empty datasets Exception
     */
    private void emptyCheckBoxException(String errorName){
        Views.buildAlert(Alert.AlertType.ERROR,"Error loading the "+ errorName, "ERROR").showAndWait();
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

        loadAlgorithms();
        loadPrecisions();

        try {
            addRatesButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"addSmall.png").toExternalForm())));
            editRatesButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"editSmall.png").toExternalForm())));
            exitButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator+ "exit.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+File.separator +"user.png").toExternalForm())));
        }
        catch(Exception e)
        {

        }

    }
}
