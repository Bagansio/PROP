package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.Optional;

public class RecommendationView {

    @FXML
    public Label itemTitleLabel;

    @FXML
    public Label idLabel;

    @FXML
    public Label itemIdLabel;

    @FXML
    public Label rateLabel;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    /**
     *
     * @param event
     */
    @FXML
    public void deleteRecommendation(ActionEvent event){
        Optional<ButtonType> result = Views.buildAlert(Alert.AlertType.CONFIRMATION,"Are you sure to delete the rate of " + titleLabel.getText() + " ?","DELETE RATE").showAndWait();
        if(result.get() == ButtonType.OK) {
            CtrlView.ctrlDomain.deleteRecommendation(idLabel.getId());
            ((VBox) idLabel.getParent().getParent()).getChildren().remove(idLabel.getParent()); // removes
        }
    }

    @FXML
    public void editRate(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit Rate");
        dialog.setHeaderText("Edit rate of \n" + idLabel.getText() + "\nExample 5");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent() && ! result.get().equals("")) {
            try {
                Double newRate = Double.parseDouble(result.get());
                if(newRate < 0 || newRate > 10) Views.buildAlert(Alert.AlertType.ERROR, "Rate should be between 0 and 10 \nExample: 5", "ERROR").showAndWait();
                else{
                    rateLabel.setText(newRate.toString());
                    CtrlView.ctrlDomain.editRateOfCurrentUser(idLabel.getText(),newRate,isKnown);
                }
            } catch (Exception e) {
                Views.buildAlert(Alert.AlertType.ERROR, "Error format of : " + result.get() + "\nExample: 5.0", "ERROR").showAndWait();
            }
        }
    }

    public void setData(Map<String,String> data){
        itemTitleLabel.setText(data.get("itemTitle"));
        idLabel.setText(data.get("id"));
        rateLabel.setText(data.get("rate"));
        itemIdLabel.setText(data.get("itemId"));
    }
}
