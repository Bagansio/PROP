package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.Controllers.CtrlAlgorithms;
import com.recommender.recommenderapp.Domain.Controllers.CtrlItems;
import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Styles;
import com.recommender.recommenderapp.View.Utils.Views;
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
        //loadButton.setStyle(Styles.getStyleBackgroundColor("black"));
        String dataset = datasetsChoiceBox.getValue();
        if(CtrlView.ctrlDomain.loadData(dataset)){
            CtrlView.changeScene("MainView");
            System.out.println(CtrlView.ctrlDomain.getItems().size());

        }
        else
            Views.buildErrorAlert("Error loading users and items of Dataset, check the files and load again").showAndWait();

    }


    /**
     * @brief handles the empty datasets Exception
     */
    private void emptyDatasetsException(){
        Views.buildErrorAlert("Should add a dataset in: "+ Utils.PATH + "\n With the 4 files needed").showAndWait();
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
        loadDatasetsChoiceBox();
    }


}
