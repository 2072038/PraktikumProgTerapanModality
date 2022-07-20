package com.pterapan.modalitydemo.controller;

import com.pterapan.modalitydemo.ModalityDemo;
import com.pterapan.modalitydemo.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierController {
    public Label labelId;
    public TextField txtId;
    public Label labelNama;
    public TextField txtNama;
    public Label labelAlamat;
    public TextField txtAlamat;
    public TableColumn<Supplier, String> columnId;
    public TableColumn<Supplier, String> columnNamaSup;
    public TableColumn<Supplier, String> columnAlamatSup;
    public TableView<Supplier> tableSupplier;

    private BarangController barangController;

    public void setBarangController(BarangController barangController) {
        this.barangController = barangController;
        tableSupplier.setItems(barangController.getSlist());
    }

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNamaSup.setCellValueFactory(new PropertyValueFactory<>("nama"));
        columnAlamatSup.setCellValueFactory(new PropertyValueFactory<>("alamat"));
    }

    public void saveSupplier(ActionEvent actionEvent) {
        String id = txtId.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        barangController.getSlist().add(new Supplier(Integer.valueOf(id), nama, alamat));
        txtId.clear();
        txtNama.clear();
        txtAlamat.clear();
    }

    public void resetSupplier(ActionEvent actionEvent) {
        txtId.clear();
        txtNama.clear();
        txtAlamat.clear();
    }

    public void getSelectedItem(MouseEvent mouseEvent) {
        txtId.setDisable(true);
        txtId.setText(String.valueOf(tableSupplier.getSelectionModel().getSelectedItem().getId()));
        txtNama.setText(tableSupplier.getSelectionModel().getSelectedItem().getNama());
        txtAlamat.setText(tableSupplier.getSelectionModel().getSelectedItem().getAlamat());
    }

    public void updateSupplier(ActionEvent actionEvent) {
        txtId.setDisable(false);
        tableSupplier.getSelectionModel().getSelectedItem().setNama(txtNama.getText());
        tableSupplier.getSelectionModel().getSelectedItem().setAlamat(txtAlamat.getText());
        txtId.clear();
        txtNama.clear();
        txtAlamat.clear();
    }
}
