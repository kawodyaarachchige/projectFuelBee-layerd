package lk.ijse.fuelBee.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.fuelBee.dao.custom.FuelDAO;
import lk.ijse.fuelBee.dao.custom.MachineDAO;
import lk.ijse.fuelBee.dao.impl.FuelDAOImpl;
import lk.ijse.fuelBee.dao.impl.MachineDAOImpl;
import lk.ijse.fuelBee.dto.FuelTypeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.model.FuelModel;
import lk.ijse.fuelBee.model.MachineModel;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class ManageFuelMachineDetailsFormController {

    public JFXButton backpane;
    public TextField txtMachineId;
    public ComboBox cmbFuelType;
    public DatePicker dpDate;
    public TextField txtAvailability;
    public TextField txtStartFuel;
    public TextField txtEndFuel;
    public TextField txtSearchMachineId;

    FuelDAO fuelDAO = new FuelDAOImpl();
    MachineDAO machineDAO = new MachineDAOImpl();

    public void initialize() throws SQLException {
        loadAllFuelType();
    }

    public void loadAllFuelType() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<FuelTypeDto> allFuelType = null;
        try {
            allFuelType = fuelDAO.getAllFuelType();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (FuelTypeDto fuelTypeDto : allFuelType) {
            obList.add(fuelTypeDto.getFuelType());
        }
        cmbFuelType.setItems(obList);
    }

    public void txtMachineIdOnAction(MouseEvent mouseEvent) {
        txtMachineId.setText(generateId("M"));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String machineId = txtMachineId.getText();
        boolean isDeleted = MachineModel.deleteIncome(machineId);
        if(isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully").show();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Not Deleted").show();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnOnUpdateAction(ActionEvent actionEvent) throws SQLException {
        String type = cmbFuelType.getValue().toString();
        String date = String.valueOf(dpDate.getValue());
        String machineId = txtMachineId.getText();
        String availability = txtAvailability.getText();
        String startFuel = txtStartFuel.getText();
        String endFuel = txtEndFuel.getText();
        String fuelId = null;
        try {
            fuelId = fuelDAO.getFuelIdByName(cmbFuelType.getValue().toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        MachineDto machineDto = new MachineDto(
                machineId,
                fuelId,
                type,
                availability,
                Integer.parseInt(startFuel),
                Integer.parseInt(endFuel),
                Date.valueOf(date)
        );
        final boolean isUpdated = MachineModel.updateMachineSpecs(machineDto);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Updated Successfully").show();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Not Updated").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String type = cmbFuelType.getValue().toString();
        String date = String.valueOf(dpDate.getValue());
        String machineId = txtMachineId.getText();
        String availability = txtAvailability.getText();
        String startFuel = txtStartFuel.getText();
        String endFuel = txtEndFuel.getText();
        String fuelId = null;
        try {
            fuelId = fuelDAO.getFuelIdByName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        MachineDto machineDto = new MachineDto(
                machineId,
                fuelId,
                type,
                availability,
                Integer.parseInt(startFuel),
                Integer.parseInt(endFuel),
                Date.valueOf(date)
        );
        boolean isMachineIdValidated = Pattern.matches("[M]\\d{1,}", machineId);
        if(!isMachineIdValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid Machine Id").show();
        }else{
            boolean isSaved = MachineModel.confirmMachineUsage(machineDto);
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Saved Successfully & Income source added").show();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Saved").show();
            }
        }
    }

    public static String generateId(String prefix) {
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYmmddHHmmss");
        String timestampString = dateFormat.format(new Date(timestamp));
        Random random = new Random();
        int randomComponent = random.nextInt(1000);
        String machineId = prefix + timestampString;
        return machineId;
    }
    public void clearFields(){
        txtMachineId.clear();
        txtAvailability.clear();
        txtStartFuel.clear();
        txtEndFuel.clear();
        cmbFuelType.setValue(null);
    }

    public void btnSearchMachineIdOnAction(ActionEvent actionEvent) throws SQLException {
        MachineDto machineDto = null;
        try {
            machineDto = machineDAO.searchMachine(txtSearchMachineId.getText());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(machineDto != null){
            txtMachineId.setText(machineDto.getMachineId());
            txtAvailability.setText(machineDto.getAvailability());
            txtStartFuel.setText(String.valueOf(machineDto.getStartFuelAmount()));
            txtEndFuel.setText(String.valueOf(machineDto.getEndFuelAmount()));
            cmbFuelType.setValue(machineDto.getFuelType());
        }else{
            txtSearchMachineId.setText("Not Found");
            new Alert(Alert.AlertType.ERROR,"Machine Not Found").show();
        }
    }
}
