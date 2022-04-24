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
public class ItemRateView {


    private static boolean isKnown;

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

    public static void setIsKnown(boolean isKnown){
        ItemRateView.isKnown = isKnown;
    }

    /**
     *
     * @param event
     */
    @FXML
    public void deleteRate(ActionEvent event){
        Optional<ButtonType> result = Views.buildAlert(Alert.AlertType.CONFIRMATION,"Are you sure to delete the rate of " + titleLabel.getText() + " ?","DELETE RATE").showAndWait();
        if(result.get() == ButtonType.OK) {
            CtrlView.ctrlDomain.deleteRateOfCurrentUser(idLabel.getText(),isKnown);
            ((VBox) idLabel.getParent().getParent()).getChildren().remove(idLabel.getParent()); // removes
        }
    }

    @FXML
    public void editRate(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Rate");
        dialog.setHeaderText("Edit rate of \n" + titleLabel.getText() + "\nExample 5.5");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent() && ! result.get().equals("")) {
            try {
                Double newRate = Double.parseDouble(result.get());
                if(newRate < 0) Views.buildAlert(Alert.AlertType.ERROR, "Rate should be positive \nExample: 5", "ERROR").showAndWait();
                else{
                    rateLabel.setText(newRate.toString());
                    CtrlView.ctrlDomain.editRateOfCurrentUser(idLabel.getText(),newRate,isKnown);
                }
            } catch (Exception e) {
                Views.buildAlert(Alert.AlertType.ERROR, "Error format of : " + result.get() + "\nExample: 5.0", "ERROR").showAndWait();
            }
        }
    }

    public void setData(String[] data){
        titleLabel.setText(data[0]);
        idLabel.setText(data[1]);
        rateLabel.setText(data[2]);
    }
}
