package lk.ijse.fuelBee.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.fuelBee.bo.BOFactory;
import lk.ijse.fuelBee.bo.custom.AdminBO;
import lk.ijse.fuelBee.bo.custom.EmployeeBO;
import lk.ijse.fuelBee.dao.custom.AdminDAO;
import lk.ijse.fuelBee.dao.custom.impl.AdminDAOImpl;
import lk.ijse.fuelBee.dto.AdminDto;
import lk.ijse.fuelBee.regex.regexPatterns;

import java.io.IOException;
import java.sql.SQLException;

public class ForgetPasswordFormController {

    public TextField txtOTP;
    public TextField txtPassword;
    public TextField txtRePassword;
    public Button btnChangePassword;
    public String tempUserName=LoginFormController.tempUserName;
    public Button btnBack;



    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ADMIN);


    public void btnChangePasswordOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("BUTTON CLICKED");
        AdminDto admin = adminBO.getAdmin(tempUserName);
        if(admin == null){
            System.out.println("bye");
            new Alert(Alert.AlertType.ERROR, "User not found | redirecting..", ButtonType.OK).showAndWait();
            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) btnChangePassword.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("Login Form");
            stage1.centerOnScreen();
        }else{
            if(txtOTP.getText().equals(String.valueOf(LoginFormController.oneTimePassword))){
                boolean isPasswordValid = regexPatterns.isPasswordValid(txtPassword.getText());
                if(isPasswordValid){
                    if(txtPassword.getText().equals(txtRePassword.getText())){
                        final boolean isPasswordChanged = adminBO.updateAdmin(admin.getEmail(), txtPassword.getText());
                        if(isPasswordChanged){
                            new Alert(Alert.AlertType.INFORMATION, "Password Changed | You will be redirected to Login Form", ButtonType.OK).showAndWait();
                            Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                            Scene scene1 = new Scene(load);
                            Stage stage1 = (Stage) btnChangePassword.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Login Form");
                            stage1.centerOnScreen();
                        }else{
                            new Alert(Alert.AlertType.ERROR, "Password Not Changed", ButtonType.OK).showAndWait();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).showAndWait();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Password must contain at least one digit, one uppercase letter, one lowercase letter and one special character", ButtonType.OK).showAndWait();
                }
            }
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene1 = new Scene(load);
        Stage stage1 = (Stage) btnBack.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Login Form");
        stage1.centerOnScreen();
    }
}
