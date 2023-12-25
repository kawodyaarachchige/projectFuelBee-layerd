package lk.ijse.fuelBee.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.fuelBee.dao.custom.FuelDAO;
import lk.ijse.fuelBee.dao.custom.OrderDAO;
import lk.ijse.fuelBee.dao.impl.FuelDAOImpl;
import lk.ijse.fuelBee.dao.impl.OrderDAOImpl;
import lk.ijse.fuelBee.dto.FuelTypeDto;
import lk.ijse.fuelBee.dto.OrderDto;
import lk.ijse.fuelBee.dto.tm.OrderTm;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

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

    OrderDAO orderDAO = new OrderDAOImpl();

    FuelDAO fuelDAO = new FuelDAOImpl();


    public void initialize() throws SQLException {
        cmbTankQty.getItems().addAll(6000,12000,18000,35000);
        cmbTankQty.setOnAction(event -> {
            if(!(cmbFuelType.getValue() ==null)){
                try {
                    int selectedValue = (int) cmbTankQty.getValue();
                    Double fuelPrice = null;
                    try {
                        fuelPrice = fuelDAO.getFuelPriceByName(cmbFuelType.getValue().toString());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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
        getAllFuelTypes();

        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                OrderTm selectedOrder = tblOrder.getItems().get(newValue.intValue());
                txtId.setText(selectedOrder.getOrderId());
                txtPrice.setText(String.valueOf(selectedOrder.getPrice()));
                cmbTankQty.setValue(selectedOrder.getTankQty());
                dpDate.setValue(selectedOrder.getDate().toLocalDate());
                cmbFuelType.setValue(selectedOrder.getType());
            }
        });
    }
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String orderId=txtId.getText();
        String email="projectfuelbee@gmail.com";
        String type = cmbFuelType.getValue().toString();
        Date date = Date.valueOf(dpDate.getValue());
        double price=Double.parseDouble(txtPrice.getText());
        int qty= (int) cmbTankQty.getValue();
        String status="NOT PAID";


        OrderDto orderDto = new OrderDto(orderId, email, type, date, qty, price, status);
        boolean isSaved = false;
        try {
            isSaved = orderDAO.save(orderDto);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        boolean isDeleted = false;
        try {
            isDeleted = orderDAO.delete(orderId);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        String type = cmbFuelType.getValue().toString();
        Date date = Date.valueOf(dpDate.getValue());
        double price=Double.parseDouble(txtPrice.getText());
        int qty= (int) cmbTankQty.getValue();
        String status="NOT PAID";

        OrderDto orderDto = new OrderDto(orderId, email, type, date, qty, price, status);
        boolean isUpdated = false;
        try {
            isUpdated = orderDAO.update(orderDto);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        dpDate.setValue(null);
        txtPrice.clear();
        cmbFuelType.setValue(null);
        cmbTankQty.setValue(null);
    }

    public void loadAllOrders() throws SQLException {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();
        ArrayList<OrderDto> allOrders = null;
        try {
            allOrders = orderDAO.getAll();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (OrderDto orderDto : allOrders) {
            obList.add(new OrderTm(orderDto.getOrderId(), orderDto.getEmail(), orderDto.getType(), orderDto.getDate(), orderDto.getTankQty(), orderDto.getPrice(), orderDto.getStatus()));
        }
        tblOrder.setItems(obList);
        tblOrder.refresh();
    }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("tankQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
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
        ArrayList<FuelTypeDto> allFuelType = null;
        try {
            allFuelType = fuelDAO.getAll();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
