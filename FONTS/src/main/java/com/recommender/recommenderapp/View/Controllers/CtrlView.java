package com.recommender.recommenderapp.View.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Paths;


public class CtrlView extends Application {

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
        Parent root = FXMLLoader.load(Paths.get(System.getProperty("user.dir") + "resources\\templates\\MainView.fxml").toUri().toURL());
        primaryStage.setTitle("OTHELLO");
        primaryStage.setScene(new Scene(root, 1464, 824));
        primaryStage.show();
    }

    /**
     * @brief Main method.
     */
    public static void main(String[] args) {
        //ViewCtrl.domainCtrl = new DomainCtrl();
        CtrlView.launch(args);
    }
}
