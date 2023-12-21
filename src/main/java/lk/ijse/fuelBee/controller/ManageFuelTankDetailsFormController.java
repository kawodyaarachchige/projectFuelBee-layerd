package lk.ijse.fuelBee.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import lk.ijse.fuelBee.dto.FuelTypeDto;
import lk.ijse.fuelBee.dto.TankDto;
import lk.ijse.fuelBee.model.FuelModel;
import lk.ijse.fuelBee.model.MachineModel;
import lk.ijse.fuelBee.model.TankModel;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class ManageFuelTankDetailsFormController {

    public TextField txtTankId;
    public ComboBox cmbTankType;
    public DatePicker dpTankDate;
    public TextField txtTankQty;
    public TextField txtTankRemainingFuel;
    public TextField txtTankWaste;
    public TextField txtSearchFuelTank;

    public void initialize() throws SQLException {
        loadAllFuelType();
    }

    public void txtTankIdOnAction(MouseEvent mouseEvent) {
        txtTankId.setText(generateId("T"));
    }

    public void btnTankSaveOnAction(ActionEvent actionEvent) throws SQLException {
            String tankId = txtTankId.getText();
            String fuelType = cmbTankType.getValue().toString();
            int qty = Integer.valueOf(txtTankQty.getText());
            int remainingFuel = Integer.valueOf(txtTankRemainingFuel.getText())-Integer.valueOf(txtTankWaste.getText());
            int capacityOfWaste = Integer.valueOf(txtTankWaste.getText());
            Date date = Date.valueOf(dpTankDate.getValue());

            TankDto tankDto = new TankDto(
                    tankId,
                    fuelType,
                    qty,
                    remainingFuel,
                    capacityOfWaste,
                    date
            );
            boolean isRemainingFuelMatched = MachineModel.checkDayEndAmounts(FuelModel.getFuelIdByName(fuelType),Integer.parseInt(txtTankRemainingFuel.getText()));
            if(!isRemainingFuelMatched){
                new Alert(Alert.AlertType.ERROR,"Remaining Fuel Amount Not Matched | Check the Machine Table again").show();
                return;
            }
            boolean isMachineIdValidated = Pattern.matches("[T]\\d{1,}", tankId);
            if(!isMachineIdValidated){
                new Alert(Alert.AlertType.ERROR,"Invalid Tank Id").show();
            }else {
                String FuelId = FuelModel.getFuelIdByName(fuelType);
                boolean isWasteAmountReduced = MachineModel.changeDayEndFuelByWaste(FuelId, capacityOfWaste);
                boolean isSaved = TankModel.saveTank(tankDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
                    /*getAllMachines();
                    loadAllMachines();*/
                    clearTankFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Not Saved").show();
                }
            }
    }

    public void btnTankDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String tankId = txtTankId.getText();
        boolean isDeleted = TankModel.deleteTank(tankId);
        if(isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully").show();
            //getAllMachines();
            clearTankFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Not Deleted").show();
        }
    }

    public void btnTankClearOnAction(ActionEvent actionEvent) {
        clearTankFields();
    }

    public void btnClearOnAction(MouseEvent mouseEvent) {
        clearTankFields();
    }

    public void btnTankUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String tankId = txtTankId.getText();
        String fuelType = cmbTankType.getValue().toString();
        int qty = Integer.valueOf(txtTankQty.getText());
        int remainingFuel = Integer.valueOf(txtTankRemainingFuel.getText());
        int capacityOfWaste = Integer.valueOf(txtTankWaste.getText());
        Date date = Date.valueOf(dpTankDate.getValue());

        TankDto tankDto = new TankDto(
                tankId,
                fuelType,
                qty,
                remainingFuel,
                capacityOfWaste,
                date
        );

        String FuelId = FuelModel.getFuelIdByName(fuelType);
        boolean isWasteAmountReduced = MachineModel.changeDayEndFuelByWaste(FuelId, capacityOfWaste);
        boolean isUpdated = TankModel.updateTank(tankDto);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Updated Successfully").show();
            clearTankFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Not Updated").show();
        }
    }
    public static String generateId(String prefix) {
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYmmddHHmmss");
        String timestampString = dateFormat.format(new Date(timestamp));
        Random random = new Random();
        int randomComponent = random.nextInt(1000);
        String tankId = prefix + timestampString;
        return tankId;
    }
    public void clearTankFields(){
        txtTankId.clear();
        cmbTankType.setValue(null);
        txtTankQty.clear();
        txtTankRemainingFuel.clear();
        txtTankWaste.clear();
        dpTankDate.setValue(null);
    }
    public void loadAllFuelType() throws SQLException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<FuelTypeDto> allFuelType = FuelModel.getAllFuelType();
        for (FuelTypeDto fuelTypeDto : allFuelType) {
            obList.add(fuelTypeDto.getFuelType());
        }
        cmbTankType.setItems(obList);
    }

    public void btnSearchFuelTankOnAction(ActionEvent actionEvent) throws SQLException {
        TankDto tankDto = TankModel.searchTank(txtSearchFuelTank.getText());
        if (tankDto != null) {
            txtTankId.setText(tankDto.getTankId());
            cmbTankType.setValue(tankDto.getFuelType());
            txtTankQty.setText(String.valueOf(tankDto.getQty()));
            txtTankRemainingFuel.setText(String.valueOf(tankDto.getRemainingFuel()));
            txtTankWaste.setText(String.valueOf(tankDto.getCapacityOfWaste()));
            //dpTankDate.setValue(LocalDate.parse(tankDto.getDate().toLocaleString()));
        }else{
            txtSearchFuelTank.setText("Not Found");
        }
    }
}
