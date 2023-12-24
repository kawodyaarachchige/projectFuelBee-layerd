package lk.ijse.fuelBee.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.fuelBee.dao.custom.*;
import lk.ijse.fuelBee.dao.impl.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DashBoardDetailsFormController {
    public Text txtLADiesel;
    public Text txtLSDiesel;
    public Text txtXMDiesel;
    public Text txtLP92;
    public Text txtXP95;
    public Text txtXPEuro3;
    public Label lblDate;
   // public Text txtDate1;
    public Text txtEmpCount;
    public Text txtOrderCount;
    public AnchorPane clanderpane;
    public BarChart barChart;

    FuelDAO fuelDAO = new FuelDAOImpl();

    OrderDAO orderDAO = new OrderDAOImpl();

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    OutcomeDAO outcomeDAO = new OutcomeDAOImpl();;

    IncomeDAO incomeDAO = new IncomeDAOImpl();;

    public void initialize() throws SQLException, IOException {
        populateBarChart();
        ArrayList<Double> fuelPrices = fuelDAO.getFuelPrices();
        txtLADiesel.setText(String.valueOf(fuelPrices.get(0)));
        txtLSDiesel.setText(String.valueOf(fuelPrices.get(1)));
        txtXMDiesel.setText(String.valueOf(fuelPrices.get(2)));
        txtLP92.setText(String.valueOf(fuelPrices.get(3)));
        txtXP95.setText(String.valueOf(fuelPrices.get(4)));
        txtXPEuro3.setText(String.valueOf(fuelPrices.get(5)));
       //txtDate1.setText(LocalDate.now().toString());
        txtOrderCount.setText(String.valueOf(orderDAO.getOrderCount()));
        txtEmpCount.setText(String.valueOf(employeeDAO.getEmployeeCount()));

        URL resource = getClass().getResource("/view/Calender.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        clanderpane.getChildren().clear();
        clanderpane.getChildren().add(load);

    }
    private void populateBarChart() throws SQLException {
        Map<String, Double> monthlyIncomes = incomeDAO.getMonthlyIncomesTotal();
        Map<String, Double> monthlyOutcomes = outcomeDAO.getMonthlyOutcomesTotal();

        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");
        for (Map.Entry<String, Double> entry : monthlyIncomes.entrySet()) {
            incomeSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        XYChart.Series<String, Number> outcomeSeries = new XYChart.Series<>();
        outcomeSeries.setName("Outcome");
        for (Map.Entry<String, Double> entry : monthlyOutcomes.entrySet()) {
            outcomeSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().addAll(incomeSeries, outcomeSeries);
    }


}
