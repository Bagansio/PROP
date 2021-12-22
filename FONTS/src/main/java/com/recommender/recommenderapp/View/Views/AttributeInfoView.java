
package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class AttributeInfoView {


    @FXML
    public Label valueLabel;

    @FXML
    public Label idLabel;


    public void setData(String[] data){
        valueLabel.setText(data[1]);
        idLabel.setText(data[0]);
    }
}
