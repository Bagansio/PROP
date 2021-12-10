package com.recommender.recommenderapp.View.Utils;

import com.recommender.recommenderapp.Data.Utils.Utils;
import javafx.scene.control.Alert;

public class Views {

    public static Alert buildErrorAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("");
        alert.setTitle("ERROR");
        alert.setContentText(msg);
        return alert;
    }


}
