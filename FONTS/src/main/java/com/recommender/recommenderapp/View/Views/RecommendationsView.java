package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Map;
/**
 * @author Alex
 */
public class RecommendationsView {

    @FXML
    public Text title;

    @FXML
    public Button backButton;

    @FXML
    public Button searchButton;

    @FXML
    public Button profileButton;

    @FXML
    public VBox ratesBox;

    @FXML
    public TextField searchText;


    @FXML
    public void goProfile(ActionEvent event){
        Views.changeToProfile(event);
    }

    @FXML
    public void searchRecommendations(ActionEvent event){
        loadRecommendations(searchText.getText());
    }


    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"MainView");
    }


    public void loadRecommendations(String itemTitle){

        ratesBox.getChildren().clear();

        String[][] recommendations = CtrlView.ctrlDomain.searchRecommendations(itemTitle);
        for(String[] recommendation : recommendations){

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(Views.getPath("RecommendationView.fxml"));
                HBox hBox = fxmlLoader.load();

                RecommendationView recommendationView = fxmlLoader.getController();
                recommendationView.setData(recommendation);
                ratesBox.getChildren().add(hBox);
            }
            catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }
        }
    }



    public void initialize() {
        loadRecommendations("");
        try {
            backButton.setGraphic(new ImageView(new Image(Views.getPath("icons" + File.separator + "back.png").toExternalForm())));
            searchButton.setGraphic(new ImageView(new Image(Views.getPath("icons" + File.separator+ "search.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator + "user.png").toExternalForm())));
        }
        catch(Exception e)
        {
            System.out.println(e.fillInStackTrace());
        }
    }

}
