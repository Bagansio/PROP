module com.recommender.recommenderapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.recommender.recommenderapp to javafx.fxml;
    exports com.recommender.recommenderapp;
}