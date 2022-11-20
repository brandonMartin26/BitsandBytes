module com.example.myjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.myjavafx to javafx.fxml;
    exports com.example.myjavafx;
}