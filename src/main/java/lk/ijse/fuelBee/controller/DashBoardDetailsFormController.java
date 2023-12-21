package lk.ijse.fuelBee.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.fuelBee.dto.OrderDto;
import lk.ijse.fuelBee.model.FuelModel;
import lk.ijse.fuelBee.model.OrderModel;
import lk.ijse.fuelBee.model.ProfitModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public void initialize() throws SQLException, IOException {
        populateBarChart();
        ArrayList<Double> fuelPrices = FuelModel.getFuelPrices();
        txtLADiesel.setText(String.valueOf(fuelPrices.get(0)));
        txtLSDiesel.setText(String.valueOf(fuelPrices.get(1)));
        txtXMDiesel.setText(String.valueOf(fuelPrices.get(2)));
        txtLP92.setText(String.valueOf(fuelPrices.get(3)));
        txtXP95.setText(String.valueOf(fuelPrices.get(4)));
        txtXPEuro3.setText(String.valueOf(fuelPrices.get(5)));
       //txtDate1.setText(LocalDate.now().toString());
        txtOrderCount.setText(String.valueOf(OrderModel.getOrderCount()));
        txtEmpCount.setText(String.valueOf(OrderModel.getEmployeeCount()));

        URL resource = getClass().getResource("/view/Calender.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        clanderpane.getChildren().clear();
        clanderpane.getChildren().add(load);

    }
    private void populateBarChart() throws SQLException {
        Map<String, Double> monthlyIncomes = ProfitModel.getMonthlyIncomesTotal();
        Map<String, Double> monthlyOutcomes = ProfitModel.getMonthlyOutcomesTotal();

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
