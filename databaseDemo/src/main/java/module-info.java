module com.pterapan.databasedemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pterapan.databasedemo to javafx.fxml;
    exports com.pterapan.databasedemo;
}