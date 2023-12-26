package lk.ijse.fuelBee.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.fuelBee.bo.BOFactory;
import lk.ijse.fuelBee.bo.custom.EmployeeBO;
import lk.ijse.fuelBee.dao.custom.EmployeeDAO;
import lk.ijse.fuelBee.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.tm.EmployeeTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class EmployeeFormController {
    public TextField txtEmployeeFirstName;
    public TextField txtEmployeeAddress;
    public TextField txtEmployeeId;
    public TextField txtEmployeeLastName;
    public TextField txtEmployeeRole;
    public TextField txtEmployeeSalary;
    public TextField txtEmployeeAge;
    public TableView<EmployeeTm> tblEmployee;
    public TableColumn<?,?> colId;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colRole;
    public TableColumn<?,?> colAddress;
    public TableColumn<?,?> colSalary;
    public TableColumn<?,?> colAge;
    public TableColumn<?,?> colOption;
    public TextField txtEmpId1;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE);

    public void initialize() throws SQLException {
        loadAllEmployees();
        setCellValueFactory();



        tblEmployee.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                EmployeeTm selectedHotel = tblEmployee.getItems().get(newValue.intValue());
                txtEmpId1.setText(selectedHotel.getId());
                txtEmployeeFirstName.setText(selectedHotel.getFirstName());
                txtEmployeeLastName.setText(selectedHotel.getLastName());
                txtEmployeeAddress.setText(selectedHotel.getAddress());
                txtEmployeeRole.setText(selectedHotel.getJobTitle());
                txtEmployeeSalary.setText(String.valueOf(selectedHotel.getSalary()));
                txtEmployeeAge.setText(String.valueOf(selectedHotel.getAge()));
            }
        });
    }
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String empId = txtEmpId1.getText();
        String firstName = txtEmployeeFirstName.getText();
        String lastName = txtEmployeeLastName.getText();
        String address = txtEmployeeAddress.getText();
        String jobTitle = txtEmployeeRole.getText();
        double salary = Double.parseDouble(txtEmployeeSalary.getText());
        int age = Integer.parseInt(txtEmployeeAge.getText());
        String email = "projectfuelbee@gmail.com";
        EmployeeDto employeeDto = new EmployeeDto(empId, firstName, lastName, address, age, salary, jobTitle, email);
        try {
            boolean isSaved = employeeBO.saveEmployee(employeeDto);
        if(isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved").show();
            clearFields();
            loadAllEmployees();
        }else{
            new Alert(Alert.AlertType.ERROR, "Employee Not Saved").show();
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String empId =txtEmpId1.getText();
        try{
            boolean isDeleted = employeeBO.deleteEmployee(empId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted").show();
                clearFields();
                loadAllEmployees();
            }else{
                new Alert(Alert.AlertType.ERROR, "Employee Not Deleted").show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String empId = txtEmpId1.getText();
        String firstName = txtEmployeeFirstName.getText();
        String lastName = txtEmployeeLastName.getText();
        String address = txtEmployeeAddress.getText();
        String jobTitle = txtEmployeeRole.getText();
        double salary = Double.parseDouble(txtEmployeeSalary.getText());
        int age = Integer.parseInt(txtEmployeeAge.getText());
        String email = "projectfuelbee@gmail.com";

        EmployeeDto employeeDto = new EmployeeDto(empId, firstName, lastName, address, age, salary, jobTitle, email);
        try{
            boolean isUpdated = employeeBO.updateEmployee(employeeDto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated").show();
                clearFields();
                loadAllEmployees();
            }else{
                new Alert(Alert.AlertType.ERROR, "Employee Not Updated").show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clearFields(){
        txtEmpId1.clear();
        txtEmployeeFirstName.clear();
        txtEmployeeLastName.clear();
        txtEmployeeAddress.clear();
        txtEmployeeRole.clear();
        txtEmployeeSalary.clear();
        txtEmployeeAge.clear();
    }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
       // colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
    }
    public void loadAllEmployees() throws SQLException {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        ArrayList<EmployeeDto> allEmployees = null;
        try {
            allEmployees = employeeBO.getAllEmployee();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(EmployeeDto dto : allEmployees){
            obList.add(new EmployeeTm(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getAge(), dto.getSalary(), dto.getJobTitle(), dto.getEmail()));
        }
        tblEmployee.setItems(obList);
        tblEmployee.refresh();
    }
    public static String generateEmployeeId() {
        String prefix = "EMP";
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        String timestampString = dateFormat.format(new Date(timestamp));
        Random random = new Random();
        int randomComponent = random.nextInt(1000);
        String empId = prefix + timestampString + randomComponent;
        return empId;
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/salarySheet1.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void txtEmpIdOnAction(MouseEvent mouseEvent) {
        txtEmpId1.setText(generateEmployeeId());
    }


}
