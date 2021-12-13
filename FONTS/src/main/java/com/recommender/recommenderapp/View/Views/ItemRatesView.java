package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemRatesView {

    @FXML
    public Button backButton;

    @FXML
    public Button addRatesButton;

    @FXML
    public Button searchButton;

    @FXML
    public Button profileButton;

    public void initialize() {
        System.out.println("Set current user:"+ CtrlView.ctrlDomain.setCurrentUser("53968"));


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
