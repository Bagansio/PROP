module com.recommender.recommenderapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.opencsv;

    opens com.recommender.recommenderapp to javafx.fxml;
    exports com.recommender.recommenderapp;
}