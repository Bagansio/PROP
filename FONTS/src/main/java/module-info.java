module com.recommender.recommenderapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.recommender.recommenderapp to javafx.fxml;
    exports com.recommender.recommenderapp;
}