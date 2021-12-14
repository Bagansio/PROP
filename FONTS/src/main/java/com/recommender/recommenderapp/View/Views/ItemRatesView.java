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

public class ItemRatesView {

    @FXML
    public Button backButton;

    @FXML
    public Button addRatesButton;

    @FXML
    public Button searchButton;

    @FXML
    public Button profileButton;

    @FXML
    public VBox ratesBox;

    @FXML
    public TextField searchText;

    @FXML
    public void searchItems(ActionEvent event){
        loadItems(searchText.getText());
    }




    public void loadItems(String itemTitle){

        ratesBox.getChildren().clear();

        String[][] rates = CtrlView.ctrlDomain.searchRatingsOfCurrentUser(itemTitle);
        for(String[] rate : rates){
            System.out.println(rate[1]);


            try {
                System.out.println(Views.getPath("ItemRateView.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(Views.getPath("ItemRateView.fxml"));
                HBox hBox = fxmlLoader.load();

                ItemRateView itemRateView = fxmlLoader.getController();
                itemRateView.setData(rate);
                ratesBox.getChildren().add(hBox);
            }
            catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }
        }
    }


    public void initialize() {

        loadItems("");
        try {
            backButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\back.png").toExternalForm())));
            addRatesButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\add.png").toExternalForm())));
            searchButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\search.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons\\user.png").toExternalForm())));
        }
        catch(Exception e)
        {

        }

    }

}
