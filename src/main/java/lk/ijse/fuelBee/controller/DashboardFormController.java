package lk.ijse.fuelBee.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutRight;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.StyleSheet;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardFormController {
    public AnchorPane mainpain;
    public AnchorPane changePain;
    public JFXButton fuelpane1;

    public JFXButton empane;
    public Text txtDate1;
    public JFXButton propane;
    public JFXButton dashpane1;
    public JFXButton orpane;
    public JFXButton suppane;
    public JFXButton paypane;
    public Label lblTime;
    public Text userName;


    public void initialize() throws IOException {
        URL resource = getClass().getResource("/view/dashboardDetails_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        changePain.getChildren().clear();
        changePain.getChildren().add(load);

        userName.setText(LoginFormController.loggedUserName);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateClock();
        txtDate1.setText(LocalDate.now().toString());

    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        //YES Or No button for Logout...
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to Logout ? ");
        alert.setContentText("Choose your option:");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            //If User press yes Scene will be changed to the Login Form
            System.out.println("User clicked Logout Button : Yes");
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainpain.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("login Form");;
            stage.centerOnScreen();

            new FadeIn(root).play();
        } else {
            System.out.println("User clicked Logout Button : No or closed the dialog");
        }
    }

    public void manageFuelOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        fuelpane1.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/managefuel_form.fxml");

        //fuelpane1.setStyle("-fx-background-color: #000000");

        new SlideInLeft(load).play();

    }
    private void setButtonUp() {
        fuelpane1.setStyle("-fx-background-color:  #F0610A");
        fuelpane1.setStyle(("-fx-font-color:  #F0610A"));
        empane.setStyle("-fx-background-color:  #F0610A");
        propane.setStyle("-fx-background-color:   #F0610A");
        dashpane1.setStyle("-fx-background-color:   #F0610A");
        orpane.setStyle("-fx-background-color:  #F0610A");
        suppane.setStyle("-fx-background-color:   #F0610A");
        paypane.setStyle("-fx-background-color:  #F0610A");
        propane.setStyle("-fx-background-color:  #F0610A");


    }

    public Parent setForm(String form) throws IOException {
        URL resource = getClass().getResource(form);
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        changePain.getChildren().clear();
        changePain.getChildren().add(load);

        return load;
    }

    public void OdersOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        orpane.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/order_form.fxml");

        new SlideInLeft(load).play();

    }

    public void manageSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        suppane.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/supplier_form.fxml");


        new SlideInLeft(load).play();
    }

    public void manageEmployeesOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        empane.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/employee_form.fxml");


        new SlideInLeft(load).play();
    }

    public void managePaymentOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        paypane.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/payment_form.fxml");


        new SlideInLeft(load).play();
    }

    public void manageDashboardOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        dashpane1.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/dashboardDetails_form.fxml");

        new SlideInLeft(load).play();
    }
    private void updateClock() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        lblTime.setText( formattedTime);
    }

    public void btnManageProfitOnAction(ActionEvent actionEvent) throws IOException {
        setButtonUp();
        propane.setStyle("-fx-background-color: linear-gradient(#fed968, #ff936a)");
        Parent load = setForm("/view/profit_form.fxml");


        new SlideInLeft(load).play();
    }
}
