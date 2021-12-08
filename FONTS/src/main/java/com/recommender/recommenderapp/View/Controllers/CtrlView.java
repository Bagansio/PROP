package com.recommender.recommenderapp.View.Controllers;

import com.recommender.recommenderapp.Domain.Controllers.CtrlDomain;
import com.recommender.recommenderapp.View.Views.DatasetsView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Paths;


public class CtrlView extends Application {


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
     * @post All stage parameters are set and the LogInView scene is shown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        CtrlView.stage = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(Paths.get(System.getProperty("user.dir") + "\\resources\\templates\\DatasetsView.fxml").toUri().toURL());
        loader.setController(new DatasetsView());

        Parent root = loader.load();

        primaryStage.setTitle("RECOMMENDER");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.getIcons().add(new Image("file:"+System.getProperty("user.dir") + "\\resources\\templates\\icons\\iconSmall.png"));
        primaryStage.show();
    }


    /**
     * @brief Main method.
     */
    public static void main(String[] args) {
        //CtrlView.domainCtrl = new DomainCtrl();

        CtrlView.launch(args);
    }
}
