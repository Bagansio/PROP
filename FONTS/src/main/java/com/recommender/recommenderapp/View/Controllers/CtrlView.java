package com.recommender.recommenderapp.View.Controllers;

import com.recommender.recommenderapp.Domain.Controllers.CtrlDomain;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author Alex
 */
public class CtrlView extends Application {


    /**
     * @brief Controller of Domain Layer
     */
    public static CtrlDomain ctrlDomain = new CtrlDomain();


    /**
     * @brief Main stage.
     */
    private static Stage stage;

    private static Scene scene;

    /**
     * @brief Class creator.
     * */
    public CtrlView() {
    }

    /**
     * @brief Event method which is executed when the program is executed
     * @pre <em>True</em>
     * @post All stage parameters are set and the DatasetsView scene is shown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(Views.getPath("DatasetsView.fxml"));

        loadScene(loader.load());

    }


    /**
     * @brief Change the current scene for a new
     * @param fxml -> filename of new scene
     */
    public static void changeScene(ActionEvent event, String fxml) {
        try {

            FXMLLoader loader = new FXMLLoader(Views.getPath(fxml+".fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            loadScene(loader.load());
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println(e.fillInStackTrace());
            e.fillInStackTrace();
        }
    }


    /**
     * @brief Loads the scene (load from a path, builds and show)
     * @param root
     * @throws IOException
     */
    private static void loadScene(Parent root) throws IOException {
        Scene scene = new Scene(root, 1280, 800);
        scene.getStylesheets().add(Views.getPath("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Views.getPath("icons" + File.separator +"iconSmall.png").toExternalForm()));
        stage.show();
    }

}
