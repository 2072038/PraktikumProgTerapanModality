package com.pterapan.modalitydemo.controller;

import com.pterapan.modalitydemo.ModalityDemo;
import com.pterapan.modalitydemo.model.Barang;
import com.pterapan.modalitydemo.model.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.io.IOException;

public class BarangController {
    public Label labelId;
    public TextField txtId;
    public Label labelNama;
    public TextField txtNama;
    public Label labelSupplier;
    public ComboBox cmb1;
    public TableColumn<Barang, String> columnId;
    public TableColumn<Barang, String> columnNama;
    public TableColumn<Barang, String> columnSupplier;
    public TableView<Barang> tableBarang;

    private Stage stage;
    private ObservableList<Barang> blist;
    private ObservableList<Supplier> slist;

    public void initialize() {
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        blist = FXCollections.observableArrayList();
        slist = FXCollections.observableArrayList();
        cmb1.setItems(slist);
        tableBarang.setItems(blist);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        columnSupplier.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSupplier().toString()));
    }

    public void goToSupplier(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(ModalityDemo.class.getResource("second_supplier.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        SupplierController sController = loader.getController();
        sController.setBarangController(this);
        stage.setTitle("Data Supplier");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public ObservableList<Supplier> getSlist() {
        return slist;
    }

    public void addBarang(ActionEvent actionEvent) {
        String id = txtId.getText();
        String nama = txtNama.getText();
        Supplier supplier = (Supplier) cmb1.getValue();
        blist.add(new Barang(Integer.valueOf(id), nama, (java.util.function.Supplier) supplier));
        txtId.clear();
        txtNama.clear();
    }

    public void resetBarang(ActionEvent actionEvent) {
        txtId.clear();
        txtNama.clear();
    }

    public void getSelectedItem(MouseEvent mouseEvent) {
        txtId.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItem().getId()));
        txtNama.setText(tableBarang.getSelectionModel().getSelectedItem().getNama());
    }

    public void updateBarang(ActionEvent actionEvent) {
        txtId.setDisable(true);
        tableBarang.getSelectionModel().getSelectedItem().setNama(txtNama.getText());
        tableBarang.getSelectionModel().getSelectedItem().setSupplier((java.util.function.Supplier) cmb1.getValue());
        txtId.clear();
        txtNama.clear();
    }
}
