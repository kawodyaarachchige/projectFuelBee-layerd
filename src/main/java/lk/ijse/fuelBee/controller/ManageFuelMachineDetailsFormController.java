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
import lk.ijse.fuelBee.bo.BOFactory;
import lk.ijse.fuelBee.bo.custom.FuelBO;
import lk.ijse.fuelBee.bo.custom.IncomeBO;
import lk.ijse.fuelBee.bo.custom.MachineBO;
import lk.ijse.fuelBee.dao.custom.FuelDAO;
import lk.ijse.fuelBee.dao.custom.IncomeDAO;
import lk.ijse.fuelBee.dao.custom.MachineDAO;
import lk.ijse.fuelBee.dao.custom.impl.FuelDAOImpl;
import lk.ijse.fuelBee.dao.custom.impl.IncomeDAOImpl;
import lk.ijse.fuelBee.dao.custom.impl.MachineDAOImpl;
import lk.ijse.fuelBee.dto.FuelDto;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class ManageFuelMachineDetailsFormController {
    MachineBO machineBO = (MachineBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.MACHINE);
    FuelBO fuelBO = (FuelBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.FUEL);

    IncomeBO incomeBO =(IncomeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.INCOME);

    public JFXButton backpane;
    public TextField txtMachineId;
    public ComboBox cmbFuelType;
    public DatePicker dpDate;
    public TextField txtAvailability;
    public TextField txtStartFuel;
    public TextField txtEndFuel;
    public TextField txtSearchMachineId;



    public void initialize() throws SQLException {
        loadAllFuelType();
    }

    public void loadAllFuelType() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<FuelDto> allFuelType = null;
        try {
            allFuelType = fuelBO.getAllFuel();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (FuelDto fuelDto : allFuelType) {
            obList.add(fuelDto.getFuelType());
        }
        cmbFuelType.setItems(obList);
    }

    public void txtMachineIdOnAction(MouseEvent mouseEvent) {
        txtMachineId.setText(generateId("M"));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String machineId = txtMachineId.getText();
        boolean isDeleted =incomeBO.deleteIncome(machineId);
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
            fuelId = fuelBO.getFuelIdByName(cmbFuelType.getValue().toString());
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
        final boolean isUpdated = machineBO.updateMachineSpecs(machineDto);
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
            fuelId = fuelBO.getFuelIdByName(type);
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
            boolean isSaved = machineBO.confirmMachineUsage(machineDto);
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
            machineDto = machineBO.searchMachine(txtSearchMachineId.getText());
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
