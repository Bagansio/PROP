package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Styles;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DatasetsView {




    @FXML
    public ChoiceBox<String> datasetsChoiceBox;


    @FXML
    public Button loadButton;


    /**
     * Creates an instance of DatasetsView
     */
    public DatasetsView(){
        System.out.println("Using Datasets VIEW");
    }

    /**
     * @brief action of click loadButton, returns the datasetsChoiceBox value
     */
    @FXML
    public void loadDataset(ActionEvent event){
        String dataset = datasetsChoiceBox.getValue();
        CtrlView.ctrlDomain.setDataset(dataset);

    }


    /**
     * @brief handles the empty datasets Exception
     */
    private void emptyDatasetsException(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("NO DATASETS TO LOAD");
        alert.setTitle("DATASET ERROR");
        alert.setContentText("Should add a dataset in: "+ Utils.PATH);
        alert.showAndWait();
        Platform.exit();
        System.exit(0);
    }


    /**
     * @brief Load values of the choice box (load the datasets)
     */
    private void loadDatasetsChoiceBox(){
        String[] datasets = CtrlView.ctrlDomain.getDatasets();
        if(datasets.length == 0) emptyDatasetsException();
        datasetsChoiceBox.getItems().addAll(datasets);
        datasetsChoiceBox.setValue(datasets[0]);
    }

    /**
     * @brief Initialize the view, set some values (ex styles)
     */
    public void initialize() {
        loadButton.setStyle(Styles.getStyleBackgroundColor("black"));
        loadButton.setOnMousePressed(e -> loadButton.setStyle(Styles.getStyleBackgroundColor("#3c3c3c")));//loadButton.setStyle("-fx-background-color: #3c3c3c"));
        loadButton.setOnMouseClicked(e -> loadButton.setStyle(Styles.getStyleBackgroundColor("black")));
        loadDatasetsChoiceBox();
    }


}