module com.pterapan.modalitydemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pterapan.modalitydemo to javafx.fxml;
    exports com.pterapan.modalitydemo;
    exports com.pterapan.modalitydemo.controller;
    exports com.pterapan.modalitydemo.model;
}