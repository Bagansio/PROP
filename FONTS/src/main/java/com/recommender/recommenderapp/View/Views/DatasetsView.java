package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DatasetsView {


    @FXML
    public ChoiceBox<String> datasetsChoiceBox;


    @FXML
    public Button loadButton;

    public DatasetsView(){
        System.out.println("CREATED");
    }


    @FXML
    public void loadDataset(ActionEvent event){
        String dataset = datasetsChoiceBox.getValue();
        CtrlView.ctrlDomain.setDataset(dataset);
    }

    private void emptyDatasetsError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("NO DATASETS TO LOAD");
        alert.setTitle("DATASET ERRROR");
        alert.setContentText("Should add a dataset in: resources/memory/datasets");
        alert.showAndWait();
        Platform.exit();
        System.exit(0);
    }

    private void loadDatasetsChoiceBox(){
        String[] datasets = CtrlView.ctrlDomain.getDatasets();
        if(datasets.length == 0) emptyDatasetsError();
        datasetsChoiceBox.getItems().addAll(datasets);
        datasetsChoiceBox.setValue(datasets[0]);
    }

    public void initialize() {
        loadDatasetsChoiceBox();
    }
}
