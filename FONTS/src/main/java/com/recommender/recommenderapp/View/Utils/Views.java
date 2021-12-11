package com.recommender.recommenderapp.View.Utils;

import com.recommender.recommenderapp.Data.Utils.Utils;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


/**
 * @brief Some Utils for views
 * @author Alex
 */
public class Views {


    /**
     * @brief builds an Alert with message = msg
     * @param msg -> to show the error
     * @return  An alert with message = msg
     */
    public static Alert buildErrorAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("");
        alert.setTitle("ERROR");
        alert.setContentText(msg);
        return alert;
    }

    /**
     * @brief get url from a file in resources
     * @param relativePath path to file from resources
     * @return Absolut path
     * @throws IOException
     */
    public static URL getPath(String relativePath) throws IOException {
        return Paths.get(System.getProperty("user.dir") + "\\FONTS\\src\\main\\resources\\" + relativePath).toUri().toURL();
    }
}
