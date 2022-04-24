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
public class ItemView {

    private static boolean isKnown;

    private String[] data;

    @FXML
    public Label titleLabel;

    @FXML
    public Label idLabel;


    @FXML
    public Button addButton;

    @FXML
    public Button moreButton;

    public static void setIsKnown(boolean isKnown){
        ItemView.isKnown = isKnown;
    }
    /**
     *
     * @param event
     *//*
    @FXML
    public void deleteRate(ActionEvent event){
        Optional<ButtonType> result = Views.buildAlert(Alert.AlertType.CONFIRMATION,"Are you sure to delete the rate of " + titleLabel.getText() + " ?","DELETE RATE").showAndWait();
        if(result.get() == ButtonType.OK) {
            CtrlView.ctrlDomain.deleteRateOfCurrentUser(idLabel.getText(),isKnown);
            ((VBox) idLabel.getParent().getParent()).getChildren().remove(idLabel.getParent()); // removes
        }
    }
    */
    @FXML
    public void more(ActionEvent event){
        ItemInfoView.setData(data);
        ItemInfoView.setIsKnown(isKnown);
        CtrlView.changeScene(event,"ItemInfoView");
    }

    @FXML
    public void add(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Rate");
        dialog.setHeaderText("Add rate of \n" + titleLabel.getText() + "\nExample 5.5");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            try {
                Double newRate = 0.0;
                if(! result.get().equals(""))
                    newRate = Double.parseDouble(result.get());

                if(newRate < 0) Views.buildAlert(Alert.AlertType.ERROR, "Rate should be positive \nExample: 5", "ERROR").showAndWait();
                else{
                    ((VBox) idLabel.getParent().getParent()).getChildren().remove(idLabel.getParent()); // removes
                    CtrlView.ctrlDomain.addRateOfCurrentUser(idLabel.getText(),newRate, isKnown);
                }
            } catch (Exception e) {
                Views.buildAlert(Alert.AlertType.ERROR, "Error format of : " + result.get() + "\nExample: 5.0", "ERROR").showAndWait();
            }
        }
    }
/*
/*
    @FXML
    public void editRate(ActionEvent event){

    }
    */

    public void setData(String[] data){
        this.data = data;
        titleLabel.setText(data[1]);
        idLabel.setText(data[0]);
    }
}
