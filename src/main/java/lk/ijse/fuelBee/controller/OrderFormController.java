package lk.ijse.fuelBee.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.FuelTypeDto;
import lk.ijse.fuelBee.dto.OrderDto;
import lk.ijse.fuelBee.dto.SupplierDto;
import lk.ijse.fuelBee.dto.tm.EmployeeTm;
import lk.ijse.fuelBee.dto.tm.OrderTm;
import lk.ijse.fuelBee.dto.tm.SupplierTm;
import lk.ijse.fuelBee.model.FuelModel;
import lk.ijse.fuelBee.model.OrderModel;
import lk.ijse.fuelBee.model.SupplierModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static lk.ijse.fuelBee.model.FuelModel.*;

public class OrderFormController {
    @FXML
    public TextField txtPrice;
    public TableView<OrderTm> tblOrder;
    public TableColumn<?,?> colId;
    public TableColumn<?,?> colType;
    public TableColumn<?,?> colQty;
    public TableColumn<?,?> colPrice;
    public TableColumn<?,?> colSupId;
    public TableColumn<?,?> colDate;
    public TextField txtId;
    @FXML
    public TextField txtType;
    public TextField txrQty;
    public TextField txtDate;
    //public ComboBox cmbSupId;
    public DatePicker dpDate;
    public ComboBox cmbFuelType;
    public TextField txtStatus;
    public TableColumn colStatus;
    public ComboBox cmbTankQty;


    public void initialize() throws SQLException {
        cmbTankQty.getItems().addAll(6000,12000,18000,35000);
        cmbTankQty.setOnAction(event -> {
            if(!(cmbFuelType.getValue() ==null)){
                try {
                    int selectedValue = (int) cmbTankQty.getValue();
                    Double fuelPrice =getFuelPriceByName(cmbFuelType.getValue().toString());
                    double price = selectedValue * fuelPrice;
                    txtPrice.setText(String.valueOf(price));
                } catch (SQLException e) {
                    txtPrice.setText(String.valueOf(0.0));
                    throw new RuntimeException(e);
                }
            }else{
               // new Alert(Alert.AlertType.ERROR,"Please Select Fuel Type").show();
            }
        });

        loadAllOrders();
        setCellValueFactory();
        //getAllSuppliers();
        getAllFuelTypes();

        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                OrderTm selectedOrder = tblOrder.getItems().get(newValue.intValue());
                txtId.setText(selectedOrder.getOrderId());
                //cmbFuelType.setValue(selectedOrder.getFuelType());
                //txtType.setText(selectedOrder.getType());
                txtPrice.setText(String.valueOf(selectedOrder.getPrice()));
                cmbTankQty.setValue(selectedOrder.getTankQty());
                dpDate.setValue(selectedOrder.getDate().toLocalDate());
                cmbFuelType.setValue(selectedOrder.getType());
                //cmbSupId.getItems(String.valueOf(selectedOrder.getSupplierId()));
            }
        });
    }
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String orderId=txtId.getText();
        String email="projectfuelbee@gmail.com";
        //String type=txtType.getText();
        String type = cmbFuelType.getValue().toString();
        Date date = Date.valueOf(dpDate.getValue());
        //String date=txtDate.getText();
        double price=Double.parseDouble(txtPrice.getText());
        int qty= (int) cmbTankQty.getValue();
        String status="NOT PAID";


        OrderDto orderDto = new OrderDto(orderId, email, type, date, qty, price, status);
        boolean isSaved = OrderModel.saveOrder(orderDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
            loadAllOrders();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saved Failed").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String orderId=txtId.getText();
        boolean isDeleted = OrderModel.deleteOrder(orderId);
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            clearFields();
            loadAllOrders();
        } else {
            new Alert(Alert.AlertType.ERROR, "Deleted Failed").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String orderId=txtId.getText();
        String email="projectfuelbee@gmail.com";
        //String type=txtType.getText();
        String type = cmbFuelType.getValue().toString();
        Date date = Date.valueOf(dpDate.getValue());
        //String date=txtDate.getText();
        double price=Double.parseDouble(txtPrice.getText());
        int qty= (int) cmbTankQty.getValue();
        String status="NOT PAID";

        OrderDto orderDto = new OrderDto(orderId, email, type, date, qty, price, status);
        boolean isUpdated = OrderModel.updateOrder(orderDto);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
            loadAllOrders();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Updated Failed").show();
        }
    }
    public void clearFields(){
        txtId.clear();
        //txtType.clear();
        dpDate.setValue(null);
        //txtDate.clear();
        txtPrice.clear();
        cmbFuelType.setValue(null);
        cmbTankQty.setValue(null);
    }

    public void loadAllOrders() throws SQLException {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();
        ArrayList<OrderDto> allOrders = OrderModel.getAllOrders();

        for (OrderDto orderDto : allOrders) {
            obList.add(new OrderTm(orderDto.getOrderId(), orderDto.getEmail(), orderDto.getType(), orderDto.getDate(), orderDto.getTankQty(), orderDto.getPrice(), orderDto.getStatus()));
        }
        tblOrder.setItems(obList);
        tblOrder.refresh();
    }

    /*public void getAllSuppliers() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<SupplierDto> allSuppliers = SupplierModel.getAllSuppliers();
        for (SupplierDto supplier : allSuppliers) {
                obList.add(supplier.getSupId());
        }
        cmbSupId.setItems(obList);
    }*/
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("tankQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        //colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
        String orderId=txtId.getText();
        String email="projectfuelbee@gmail.com";
        String type=txtType.getText();
        Date date= Date.valueOf(txtDate.getText());
        int qty=Integer.parseInt(txrQty.getText());
        double price=Double.parseDouble(txtPrice.getText());
        String status=txtStatus.getText();

        OrderDto orderDto = new OrderDto(orderId, email, type, date, qty, price, status);

    }
    public void getAllFuelTypes() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<FuelTypeDto> allFuelType = getAllFuelType();
        for (FuelTypeDto fuelType : allFuelType) {
            obList.add(fuelType.getFuelType());
        }
        cmbFuelType.setItems(obList);
    }

    public void txtOrderIdOnAction(MouseEvent mouseEvent) {
        txtId.setText(generateOrderId());
    }
    public static String generateOrderId() {
        String prefix = "ORD";
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        String timestampString = dateFormat.format(new Date(timestamp));
        Random random = new Random();
        int randomComponent = random.nextInt(1000);
        String ordId = prefix + timestampString + randomComponent;
        return ordId;
    }
}
