package lk.ijse.fuelBee.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fuelBee.dto.FuelDto;
import lk.ijse.fuelBee.dto.FuelTypeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.dto.TankDto;
import lk.ijse.fuelBee.dto.tm.EmployeeTm;
import lk.ijse.fuelBee.dto.tm.MachineTm;
import lk.ijse.fuelBee.dto.tm.TankTm;
import lk.ijse.fuelBee.model.FuelModel;
import lk.ijse.fuelBee.model.MachineModel;
import lk.ijse.fuelBee.model.TankModel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

public class ManagefuelFormController {
    public TextField txtMachineId;
    public TextField txtAvailability;
    public TextField txtStartFuel;
    public TextField txtEndFuel;
    public TableView<MachineTm> tblMachine;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colType;
    public TableColumn<?, ?> colAvailability;
    public TableColumn<?, ?> colDate;
    public TableColumn<?, ?> colSold;
    public TableColumn<?, ?> colRemain;
    public TextField txtFuelId;
    public ComboBox cmbFuelType;
    public TableView<TankTm> tblTanks;
    public TableColumn<?, ?> colTankId;
    public TableColumn<?, ?> colFuelType;
    public TableColumn<?, ?> colTDate;
    public TableColumn<?, ?> colTQty;
    public TableColumn<?, ?> colTRemainingFuel;
    public TableColumn<?, ?> colWasteFuel;
    public DatePicker dpDate;
    public TextField txtTankId;
    public TextField txtTankQty;
    public TextField txtTankRemainingFuel;
    public TextField txtTankWaste;
    public DatePicker dpTankDate;
    public ComboBox cmbTankType;
    public AnchorPane fuelpane;
    public Button btnAddFuel;
    public Button btnAddFuelTank;

    public void initialize() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        loadAllMachines();
        getAllMachines();
        setCellValueFactory();

        ArrayList<MachineDto> capacityLowFuels = MachineModel.getCapacityLowFuels();
        if (!capacityLowFuels.isEmpty()) {

            ArrayList<String> fuelTypes = new ArrayList<>();
            for (MachineDto machineDto : capacityLowFuels) {
                fuelTypes.add(machineDto.getFuelType());
            }

            new Alert(Alert.AlertType.WARNING, "Some Fuel Tanks are running low ! " + "\n" +fuelTypes).showAndWait();
        } else {
            System.out.println("User clicked (Not Right Now)Button or closed the dialog");
        }
    }


    public void loadAllMachines() throws SQLException {
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();
        ArrayList<MachineDto> allMachine = MachineModel.getAllMachine();

        for (MachineDto machineDto : allMachine) {
            obList.add(new MachineTm(
                    machineDto.getMachineId(),
                    machineDto.getFuelId(),
                    machineDto.getFuelType(),
                    machineDto.getAvailability(),
                    String.valueOf(machineDto.getEndFuelAmount()),
                    String.valueOf(machineDto.getStartFuelAmount()-machineDto.getEndFuelAmount()),
                    machineDto.getDate()
            ));
        }
        tblMachine.setItems(obList);
        tblMachine.refresh();
    }
    public void getAllMachines() throws SQLException {
        ObservableList<TankTm> obList = FXCollections.observableArrayList();
        ArrayList<TankDto> allTank = TankModel.getAllTank();
        for(TankDto tankDto : allTank){
            obList.add(new TankTm(
                    tankDto.getTankId(),
                    tankDto.getFuelType(),
                    String.valueOf(tankDto.getQty()),
                    String.valueOf(tankDto.getRemainingFuel()),
                    String.valueOf(tankDto.getCapacityOfWaste()),
                    (Date)tankDto.getDate()
            ));
        }
        tblTanks.setItems(obList);
        tblTanks.refresh();
    }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colSold.setCellValueFactory(new PropertyValueFactory<>("endFuelAmount"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("startFuelAmount"));
        colTankId.setCellValueFactory(new PropertyValueFactory<>("tankId"));
        colFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        colTQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTRemainingFuel.setCellValueFactory(new PropertyValueFactory<>("remainingFuel"));
        colWasteFuel.setCellValueFactory(new PropertyValueFactory<>("capacityOfWaste"));
        colTDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void btnAddFuelOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/managefuelDetails_form.fxml"));
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle("Add fuel");
        newStage.show();
    }

    public void btnAddFuelTankOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/managefueltankdetails_form.fxml"));
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle("Manage Fuel Tanks");
        newStage.show();
    }
}
