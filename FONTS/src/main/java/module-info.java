module com.recommender.recommenderapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.recommender.recommenderapp to javafx.fxml;
    exports com.recommender.recommenderapp;
    exports com.recommender.recommenderapp.View.Controllers;
    exports com.recommender.recommenderapp.View.Views;
}