package com.recommender.recommenderapp.View.Views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemRateView {


    @FXML
    public Label titleLabel;

    @FXML
    public Label idLabel;

    @FXML
    public Label rateLabel;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    public void setData(String[] data){
        titleLabel.setText(data[0]);
        idLabel.setText(data[1]);
        rateLabel.setText(data[2]);
    }
}
