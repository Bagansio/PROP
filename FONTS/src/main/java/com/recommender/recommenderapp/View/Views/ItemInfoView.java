package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Optional;

public class ItemInfoView {

    private static String[] data;

    private static boolean isKnown;

    @FXML
    public Label idLabel;

    @FXML
    public Label titleLabel;

    @FXML
    public Button backButton;

    @FXML
    public Button profileButton;


    @FXML
    public Button addButton;

    @FXML
    public VBox ratesBox;


    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"ItemAddView");
    }

    @FXML
    public void add(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Rate");
        dialog.setHeaderText("Add rate of \n" + titleLabel.getText() + "\nExample 5.5");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            try {
                Double newRate = 0.0;
                if(! result.get().equals(""))
                    newRate = Double.parseDouble(result.get());

                if(newRate < 0) Views.buildAlert(Alert.AlertType.ERROR, "Rate should be positive \nExample: 5", "ERROR").showAndWait();
                else{
                    CtrlView.ctrlDomain.addRateOfCurrentUser(idLabel.getText(),newRate, isKnown);
                    CtrlView.changeScene(event,"ItemAddView");
                }
            } catch (Exception e) {
                Views.buildAlert(Alert.AlertType.ERROR, "Error format of : " + result.get() + "\nExample: 5.0", "ERROR").showAndWait();
            }
        }
    }

    @FXML
    public void goProfile(ActionEvent event){
        Views.changeToProfile(event);
    }

    public static void setData(String[] data){
        ItemInfoView.data = data;
    }

    public static void setIsKnown(boolean isKnown){
        ItemInfoView.isKnown = isKnown;
    }

    public void loadItem(){

        idLabel.setText(data[0]);
        titleLabel.setText(data[1]);
        for(int i = 2; i < data.length; ++i){
            try {
                String[] attributeData = data[i].split("-");
                FXMLLoader fxmlLoader = new FXMLLoader(Views.getPath("AttributeInfoView.fxml"));
                HBox hBox = fxmlLoader.load();

                AttributeInfoView attributeInfoView = fxmlLoader.getController();
                attributeInfoView.setData(attributeData);
                ratesBox.getChildren().add(hBox);
            }
            catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }
        }
    }



    public void initialize() {
        loadItem();
        try {
            backButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"back.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"user.png").toExternalForm())));
        }
        catch(Exception e)
        {
            System.out.println(e.fillInStackTrace());
        }
    }

}
