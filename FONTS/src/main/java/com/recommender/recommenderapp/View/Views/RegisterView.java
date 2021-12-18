package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterView {




    @FXML
    public TextField userIdText;

    @FXML
    public Label errorText;

    @FXML
    public Button registerButton;

    @FXML
    public Button backButton;

    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"LoginUserView");
    }

    @FXML
    public void register(ActionEvent event){
        if(! userIdText.getText().equals("")){
            if(CtrlView.ctrlDomain.createUser(userIdText.getText())){
                CtrlView.ctrlDomain.changeToTempDataset();
                CtrlView.changeScene(event,"MainView");
            }

            else{
                errorText.setText("ERROR: USER " + userIdText.getText() + " ALREADY EXISTS");

            }
        }
        else{
            errorText.setText("ERROR: ADD A USER ID");
        }
        userIdText.setText("");
    }

    /**
     *
     * Creates an instance of LoginUserView
     */
    public RegisterView(){
        System.out.println("Using Register VIEW");
    }
}
