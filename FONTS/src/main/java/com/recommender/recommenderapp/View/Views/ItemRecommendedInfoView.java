package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;


/**
 * @author Alex
 */
public class ItemRecommendedInfoView {

    @FXML
    public Label itemTitleLabel;

    @FXML
    public Label idLabel;

    @FXML
    public Label expectedRate;


    public void setData(String[] data){
        idLabel.setText(data[0]);
        itemTitleLabel.setText(data[1]);
        expectedRate.setText(data[2]);
    }
}
