package lk.ijse.fuelBee.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fuelBee.dao.custom.AdminDAO;
import lk.ijse.fuelBee.dao.impl.AdminDAOImpl;
import lk.ijse.fuelBee.dto.AdminDto;
import lk.ijse.fuelBee.regex.regexPatterns;

import java.io.IOException;
import java.sql.SQLException;

public class SignupFormController {
    public TextField txtemail;
    public TextField txtCpwd;
    public TextField txtpwd;
    public JFXButton btnsign;
    public TextField txtUserName;
    public AnchorPane mainpain;

    AdminDAO adminDAO = new AdminDAOImpl();


    public void btnSignUpOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        String userName = txtUserName.getText();
        String email = txtemail.getText();
        String pwd = txtpwd.getText();
        String type="ADMIN";
        regexPatterns regexPatterns = new regexPatterns();

        boolean emailValid = regexPatterns.isEmailValid(email);
        if (!emailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email Address Format", ButtonType.OK).showAndWait();
            return;
        }else{
            if(!pwd.equals(txtCpwd.getText())){
                new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).showAndWait();
                return;
            }else{
                boolean passwordValid = regexPatterns.isPasswordValid(pwd);
                if(!passwordValid){
                    new Alert(Alert.AlertType.ERROR, "Password must contain at least one digit, one uppercase letter, one lowercase letter and one special character", ButtonType.OK).showAndWait();
                    return;
                }else{
                    AdminDto adminDto = new AdminDto(email, pwd, userName, type);
                    boolean isSaved = adminDAO.saveAdmin(adminDto);

                    if (isSaved) {
                        clearFields();
                        new Alert(Alert.AlertType.INFORMATION, "Successfully Account created", ButtonType.OK).showAndWait();
                        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) mainpain.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Dashboard Form");
                        stage.centerOnScreen();

                        new FadeIn(root).play();
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Not created | Try Again", ButtonType.OK).showAndWait();
                    }}

            }
        }
    }

    public void txtLogInOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) mainpain.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();

        new SlideInDown(root).play();
    }
    public void clearFields() {
        txtemail.clear();
        txtCpwd.clear();
        txtpwd.clear();
        txtUserName.clear();
    }
}

