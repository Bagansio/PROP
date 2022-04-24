package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.Controllers.CtrlItems;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;
import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * @author Melanie
 */
public class LoginUserView {




    @FXML
    public TextField userIdText;


    @FXML
    public Button loadButton;

    @FXML
    public Button registerButton;

    @FXML
    public Button backButton;

    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"DatasetsView");
    }

    @FXML
    public void register(ActionEvent event){
        CtrlView.changeScene(event,"RegisterView");
    }

    /**
     *
     * Creates an instance of LoginUserView
     */
    public LoginUserView(){

    }

    /**
     * @brief action of click loadButton, returns the usersIdChoiceBox value
     */
    @FXML
    public void loadUser(ActionEvent event){
        String userId = userIdText.getText();
        //check if the user id exist
        //equals("2") to test

        if(CtrlView.ctrlDomain.existsUser(userId)){
            //set the current user
            CtrlView.ctrlDomain.setCurrentUser(userId);
            //send to the main view
            CtrlView.changeScene(event,"MainView");
        }
        else
            Views.buildAlert(Alert.AlertType.ERROR,"This user id doesn't exist, please try an other one." + userId,"ERROR").showAndWait();
    }



}
