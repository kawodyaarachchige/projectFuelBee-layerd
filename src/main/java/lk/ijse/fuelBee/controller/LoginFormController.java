package lk.ijse.fuelBee.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.fuelBee.util.Mail;
import lk.ijse.fuelBee.bo.BOFactory;
import lk.ijse.fuelBee.bo.custom.AdminBO;

import lk.ijse.fuelBee.dto.AdminDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassowrd;
    public AnchorPane mainpain;

    public static String loggedUserName;
    public Text txtForgetPassword;

    public static String tempUserName;

    public static int oneTimePassword;


    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ADMIN);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Login Clicked");
        String userName = txtUserName.getText();
        String password = txtPassowrd.getText();

        if(!userName.isEmpty() || !password.isEmpty()){
                    try {
                        AdminDto admin = adminBO.getAdmin(userName);

                        if (admin != null) {
                            System.out.println(admin.getPassword());
                            if (password.equals(admin.getPassword())) {
                                loggedUserName= admin.getUsername();
                                Mail mail = new Mail();
                                mail.setMsg("Welcome..! \n\n\tYou are successfully logged in to the FuelBee Fuel Management system\n\n\t Date & Time :"+ LocalDateTime.now() +"\n\nThank you..!\n\nRegards,\nFuelBee Team");
                                mail.setTo(admin.getEmail());
                                mail.setSubject("Fuel Bee Login");

                                Thread thread = new Thread(mail);
                                thread.start();

                                Parent load = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                                Scene scene1 = new Scene(load);
                                Stage stage1 = (Stage) mainpain.getScene().getWindow();
                                stage1.setScene(scene1);
                                stage1.setTitle("DashBoard Form");
                                stage1.centerOnScreen();

                                new FadeIn(load).play();

                            } else {
                                new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Wrong UserName").show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Empty Fields").show();}
                }


    public void signupOnAction(ActionEvent actionEvent) {

        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
            Scene scene1 = new Scene(load);
            Stage stage1 = (Stage) mainpain.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.setTitle("SignUp Form");
            stage1.centerOnScreen();

            new SlideInUp(load).play();
        } catch (IOException e) {
            e.printStackTrace();
        };
        }

    public void txtForgetPasswordOnAction(MouseEvent mouseEvent) {

        if(!txtUserName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to Change Your Password ?");
            alert.setContentText("Send OTP To Your Email");

            ButtonType buttonTypeYes = new ButtonType("OK");
            ButtonType buttonTypeNo = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {
                    try {
                        oneTimePassword = generateOTP();
                        Mail mail = new Mail();
                        mail.setMsg("Hello," + "\n\n\tAn OTP Request Detected at :  " + LocalDateTime.now() + " \n\n\tOTP : " + oneTimePassword + " \n\nThank You,\n" +
                                "FuelBee Support Team");
                        mail.setTo(txtUserName.getText());
                        tempUserName=txtUserName.getText();
                        mail.setSubject("OTP Verification");

                        Thread thread = new Thread(mail);
                        thread.start();

                        Parent load = FXMLLoader.load(getClass().getResource("/view/forgetPassword_form.fxml"));
                        Scene scene1 = new Scene(load);
                        Stage stage1 = (Stage) txtForgetPassword.getScene().getWindow();
                        stage1.setScene(scene1);
                        stage1.setTitle("Change Password Form");
                        stage1.centerOnScreen();

                        new SlideInUp(load).play();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (response == buttonTypeNo) {
                    System.out.println("User Canceled the Operation");
                }
            });
        }else{
            new Alert(Alert.AlertType.ERROR, "Empty Fields | Enter Valid User Name").show();
        }

    }

    public static int generateOTP(){
        Random random = new Random();
        int password = random.nextInt(9000000) + 1000000;
        System.out.println(password);
        return password;
    }
}

