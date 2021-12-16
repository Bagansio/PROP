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

public class ItemRatesView {

    private Boolean isKnown;

    @FXML
    public Text title;

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
    public Button knownButton;

    @FXML
    public Button unknownButton;

    @FXML
    public void setKnown(ActionEvent event){
        setIsKnown(true);
    }

    @FXML
    public void setUnknown(ActionEvent event){
        setIsKnown(false);
    }

    @FXML
    public void searchItems(ActionEvent event){
        loadItems(searchText.getText());
    }


    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"MainView");
    }


    public void loadItems(String itemTitle){

        ratesBox.getChildren().clear();

        String[][] rates;
            rates = CtrlView.ctrlDomain.searchRatingsOfCurrentUser(itemTitle,isKnown);
        for(String[] rate : rates){


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


    private void setIsKnown(boolean isKnown){
        this.isKnown = isKnown;
        knownButton.setDisable(isKnown);
        unknownButton.setDisable(! isKnown);
        ItemRateView.setIsKnown(isKnown);
        if(isKnown)
            title.setText("KNOWN RATES");
        else
            title.setText("UNKNOWN RATES");

        loadItems("");
    }


    public void initialize() {
        setIsKnown(true);
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
