package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.Optional;


/**
 * @author Alex
 */
public class RecommendationView {

    @FXML
    public Label itemTitleLabel;

    @FXML
    public Label idLabel;

    @FXML
    public Label expectedRate;

    @FXML
    public Label rateLabel;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button moreButton;

    /**
     *
     * @param event
     */
    @FXML
    public void deleteRecommendation(ActionEvent event){
        Optional<ButtonType> result = Views.buildAlert(Alert.AlertType.CONFIRMATION,"Are you sure to delete the rate of " + idLabel.getText() + " ?","DELETE RATE").showAndWait();
        if(result.get() == ButtonType.OK) {
            CtrlView.ctrlDomain.deleteRecommendation(idLabel.getText());
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
                Integer newRate = Integer.parseInt(result.get());
                if(newRate < 0 || newRate > 10) Views.buildAlert(Alert.AlertType.ERROR, "Rate should be between 0 and 10 \nExample: 5", "ERROR").showAndWait();
                else{
                    rateLabel.setText(newRate.toString());
                    CtrlView.ctrlDomain.editScoreRecommendation(idLabel.getText(),newRate);
                }
            } catch (Exception e) {
                Views.buildAlert(Alert.AlertType.ERROR, "Error format of : " + result.get() + "\nExample: 5", "ERROR").showAndWait();
            }
        }
    }


    @FXML
    public void moreInfo(ActionEvent event){
        RecommendationInfoView.setRecommendationId(idLabel.getText());
        CtrlView.changeScene(event,"RecommendationInfoView");
    }

    public void setData(String[] data){
        idLabel.setText(data[0]);
        rateLabel.setText(data[3]);
        String[] itemData = data[4].split("-");
        itemTitleLabel.setText(itemData[1]);
        expectedRate.setText(itemData[2]);
    }
}
