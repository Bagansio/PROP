package com.recommender.recommenderapp.View.Controllers;

import com.recommender.recommenderapp.Domain.Controllers.CtrlDomain;
import com.recommender.recommenderapp.Domain.Controllers.CtrlItems;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;
import com.recommender.recommenderapp.View.Views.DatasetsView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

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
        FXMLLoader loader = new FXMLLoader(Paths.get(System.getProperty("user.dir") + "\\resources\\templates\\DatasetsView.fxml").toUri().toURL());
        loader.setController(new DatasetsView());

        loadScene(loader.load());

    }


    public static void changeScene(String fxml) {
        try {

            FXMLLoader loader = new FXMLLoader(Paths.get(System.getProperty("user.dir") + "\\resources\\templates\\"+fxml+".fxml").toUri().toURL());

            loader.setController(new DatasetsView());
            loadScene(loader.load());
        }
        catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }


    public void newWindow(String fxml){

    }
    private static void loadScene(Parent root) throws IOException {
        stage.hide();
        Scene scene = new Scene(root, 1280, 800);
        scene.getStylesheets().add(Paths.get(System.getProperty("user.dir") + "\\resources\\templates\\styles.css").toUri().toURL().toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:"+System.getProperty("user.dir") + "\\resources\\templates\\icons\\iconSmall.png"));
        stage.show();
    }

}
