package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Optional;

public class RecommendationInfoView {

    private static String recommendationId;

    @FXML
    public Label idLabel;

    @FXML
    public Label rateLabel;

    @FXML
    public Label precisionLabel;

    @FXML
    public Label algorithmLabel;

    @FXML
    public Button backButton;

    @FXML
    public Button profileButton;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    @FXML
    public VBox ratesBox;


    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"RecommendationsView");
    }

    @FXML
    public void deleteRecommendation(ActionEvent event){
        Optional<ButtonType> result = Views.buildAlert(Alert.AlertType.CONFIRMATION,"Are you sure to delete the rate of " + idLabel.getText() + " ?","DELETE RATE").showAndWait();
        if(result.get() == ButtonType.OK) {
            CtrlView.ctrlDomain.deleteRecommendation(idLabel.getText());
            CtrlView.changeScene(event,"RecommendationsView");
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

    public static void setRecommendationId(String recommendationId){
        RecommendationInfoView.recommendationId = recommendationId;
    }

    public void loadRecommendation(){

        String[] recommendationData = CtrlView.ctrlDomain.getRecommendationData(recommendationId);
        idLabel.setText(recommendationData[0]);
        algorithmLabel.setText(recommendationData[1]);
        precisionLabel.setText(recommendationData[2]);
        rateLabel.setText(recommendationData[3]);
        for(int i = 4; i < recommendationData.length; ++i){
            try {
                String[] itemData = recommendationData[i].split("-");
                FXMLLoader fxmlLoader = new FXMLLoader(Views.getPath("ItemRecommendedInfoView.fxml"));
                HBox hBox = fxmlLoader.load();

                ItemRecommendedInfoView itemRecommendedInfoView = fxmlLoader.getController();
                itemRecommendedInfoView.setData(itemData);
                ratesBox.getChildren().add(hBox);
            }
            catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }
        }
    }



    public void initialize() {
        loadRecommendation();
        try {
            backButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"back.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"user.png").toExternalForm())));
        }
        catch(Exception e)
        {
            System.out.println(e.fillInStackTrace());
        }
    }

}
