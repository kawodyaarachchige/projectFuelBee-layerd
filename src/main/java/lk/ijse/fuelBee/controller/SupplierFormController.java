package lk.ijse.fuelBee.controller;

import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.fuelBee.Mail;
import lk.ijse.fuelBee.bo.BOFactory;
import lk.ijse.fuelBee.bo.custom.SupplierBO;
import lk.ijse.fuelBee.dao.custom.SupplierDAO;
import lk.ijse.fuelBee.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.fuelBee.dto.SupplierDto;
import lk.ijse.fuelBee.dto.tm.SupplierTm;
import lk.ijse.fuelBee.qr.QRGenerator;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierFormController {

    public TextField txtSupName;
    public TextField txtAddress;
    public TextField txtSupId;
    public TextField txtEmail;
    public TextField txtContact;
    public TableView<SupplierTm> tblSupplier;
    public TableColumn<?,?> colId;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colEmail;
    public TableColumn<?,?> colAddress;
    public TableColumn<?,?> colContact;
    public int numOfSuppliers;

    public static String newId;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SUPPLIER);
    public void initialize() throws SQLException {
        loadAllSuppliers();
        setCellValueFactory();

        tblSupplier.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                SupplierTm selectedSupplier = tblSupplier.getItems().get(newValue.intValue());
                txtSupId.setText(selectedSupplier.getSupId());
                txtSupName.setText(selectedSupplier.getName());
                txtEmail.setText(selectedSupplier.getSup_email());
                txtAddress.setText(selectedSupplier.getAddress());
                txtContact.setText(String.valueOf(selectedSupplier.getContact()));
            }
        });
    }
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, WriterException {
        String supId = txtSupId.getText();
        String name = txtSupName.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String fuelType = "Lanka Petrol 92";

        SupplierDto supplierDto = new SupplierDto(supId, name, fuelType, Integer.parseInt(contact), address, email);

        boolean isSaved = false;
        try {
            isSaved = supplierBO.saveSupplier(supplierDto);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isSaved) {

            newId = supId;

            String values =supId + "," + name ;

            String filepath = "/home/kitty99/IdeaProjects/SupplierQr/"+ supId + ".png";
            boolean isGenerated = QRGenerator.generateQrCode(values, 1250, 1250, filepath);

            if(isGenerated){
                Mail mail = new Mail();
                mail.setMsg("Your QR Code has been generated!");
                mail.setTo(txtEmail.getText());
                mail.setSubject("Membership");
                mail.setFile(new File(filepath));

                Thread thread = new Thread(mail);
                thread.start();
            }else{
                System.out.println("QR Code Not Generated");
            }
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved").show();
            clearFields();
            loadAllSuppliers();
        }else{
            new Alert(Alert.AlertType.ERROR, "Supplier Not Saved").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtSupId.getText();

        boolean isDeleted = false;
        try {
            isDeleted = supplierBO.deleteSupplier(id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted").show();
            clearFields();
            loadAllSuppliers();
        }else{
            new Alert(Alert.AlertType.ERROR, "Supplier Not Deleted").show();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }


    public void loadAllSuppliers() throws SQLException {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        ArrayList<SupplierDto> allSuppliers = null;
        try {
            allSuppliers = supplierBO.getAllSuppliers();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (SupplierDto supplierDto : allSuppliers) {
            obList.add(new SupplierTm(
                    supplierDto.getSupId(),
                    supplierDto.getName(),
                    supplierDto.getFuelType(),
                    supplierDto.getContact(),
                    supplierDto.getAddress(),
                    supplierDto.getSup_email(),
                    new Button("Delete")
            ));
        }
        tblSupplier.setItems(obList);
        tblSupplier.refresh();
        numOfSuppliers=obList.size();
    }
    public void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("sup_email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void clearFields(){
        txtSupId.clear();
        txtAddress.clear();
        txtSupName.clear();
        txtEmail.clear();
        txtContact.clear();
    }

    public void txtSupplierIdOnAction(MouseEvent mouseEvent) {
        txtSupId.setText("SUP-0"+(numOfSuppliers+1));
    }

    public void btnOnUpdateAction(ActionEvent actionEvent) throws SQLException {
        String id = txtSupId.getText();
        String name = txtSupName.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String fuelType = "Lanka Petrol 92";

        SupplierDto supplierDto = new SupplierDto(id, name, fuelType, Integer.parseInt(contact), address, email);
        boolean isUpdated = false;
        try {
            isUpdated = supplierBO.updateSupplier(supplierDto);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated").show();
            clearFields();
            loadAllSuppliers();
        }else{
            new Alert(Alert.AlertType.ERROR, "Supplier Not Updated").show();
        }
    }

    public void sendMailOnAction(ActionEvent actionEvent) {
         SupplierTm tm = tblSupplier.getSelectionModel().getSelectedItem();

         String email = tm.getSup_email();
        Mail mail = new Mail();
        mail.setTo(email);
        mail.setSubject("Fuel Bee");
        mail.setMsg("Reminder...!! \n\nYou have upcoming order \n\nIf you have any question.contact us..011-1234567\n\nThank you.");

        final Thread thread = new Thread(mail);
        thread.start();

        new Alert(Alert.AlertType.CONFIRMATION, "Email Sent").show();
    }
}
