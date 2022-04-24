package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;


/**
 * @author Melanie
 */
public class ProfileView {

    @FXML
    public Button addRatesButton;

    @FXML
    public Button editRatesButton;

    @FXML
    public Button backButton;

    @FXML
    public Button editUserIdButton;

    @FXML
    public Label userIdLabel;

    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"MainView");
    }


    /**
            * Creates an instance of MainView
     */
    public ProfileView(){

    }

    @FXML
    public void editUserId(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit id");
        dialog.setHeaderText("Edit id of \n" + userIdLabel.getText());
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent() && ! result.get().equals("")) {
            if(CtrlView.ctrlDomain.existsUser(result.get()))
                Views.buildAlert(Alert.AlertType.ERROR, "User id " + result.get() + " already exists", "ERROR").showAndWait();
            else{
                    CtrlView.ctrlDomain.editCurrentUserId(result.get());
                    userIdLabel.setText(result.get());
            }
        }
    }

    @FXML
    public void edit(ActionEvent event){
        CtrlView.changeScene(event,"ItemRatesView");
    }

    @FXML
    public void history(ActionEvent event) { CtrlView.changeScene(event,"RecommendationsView");}

    @FXML
    public void add(ActionEvent event){CtrlView.changeScene(event,"ItemAddView");}

    public void initialize() {
        userIdLabel.setText(CtrlView.ctrlDomain.getCurrentUserId());
    }
}
