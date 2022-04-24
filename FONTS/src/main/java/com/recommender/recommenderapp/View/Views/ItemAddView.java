package com.recommender.recommenderapp.View.Views;

import com.recommender.recommenderapp.View.Controllers.CtrlView;
import com.recommender.recommenderapp.View.Utils.Views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;


/**
 * @author  Alex
 */
public class ItemAddView {

    private boolean isKnown;

    @FXML
    public Text title;

    @FXML
    public Button backButton;


    @FXML
    public Button searchButton;

    @FXML
    public Button profileButton;

    @FXML
    public VBox addBox;

    @FXML
    public TextField searchText;

    @FXML
    public Button knownButton;

    @FXML
    public Button unknownButton;


    @FXML
    public void setKnown(ActionEvent event){
        setIsKnown(true);
    }

    @FXML
    public void setUnknown(ActionEvent event){
        setIsKnown(false);
    }

    @FXML
    public void goProfile(ActionEvent event){
        Views.changeToProfile(event);
    }


    @FXML
    public void searchItems(ActionEvent event){
        loadItems(searchText.getText());
    }


    @FXML
    public void back(ActionEvent event){
        CtrlView.changeScene(event,"MainView");
    }


    public void loadItems(String itemTitle){

        addBox.getChildren().clear();

        String[][] items;
        items = CtrlView.ctrlDomain.searchItems(itemTitle,isKnown);
        System.out.println(items.length);
        for(String[] item : items){


            try {

                FXMLLoader fxmlLoader = new FXMLLoader(Views.getPath("ItemView.fxml"));
                HBox hBox = fxmlLoader.load();

                ItemView itemView = fxmlLoader.getController();
                itemView.setData(item);
                addBox.getChildren().add(hBox);
            }
            catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }
        }
    }




    private void setIsKnown(boolean isKnown){
        this.isKnown = isKnown;
        knownButton.setDisable(isKnown);
        unknownButton.setDisable(! isKnown);
        ItemView.setIsKnown(isKnown);
        if(isKnown)
            title.setText("KNOWN RATES");
        else
            title.setText("UNKNOWN RATES");

        loadItems("");
    }


    public void initialize() {
        setIsKnown(true);
        loadItems("");
        try {
            backButton.setGraphic(new ImageView(new Image(Views.getPath("icons"+ File.separator +"back.png").toExternalForm())));
            searchButton.setGraphic(new ImageView(new Image(Views.getPath("icons" + File.separator + "search.png").toExternalForm())));
            profileButton.setGraphic(new ImageView(new Image(Views.getPath("icons" + File.separator +"user.png").toExternalForm())));
        }
        catch(Exception e)
        {

        }

    }

}
