package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.ReportsBO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.IncomeDto;
import lk.ijse.fuelBee.dto.OutcomeDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;import java.util.HashMap;

public class ReportsBOImpl implements ReportsBO {
    public JasperPrint SalaryReport() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/salarySheet1.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
        return jasperPrint;

    }

    public JasperPrint paymentReport() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/paymentHistory1.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                Dbconnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
        return jasperPrint;
    }

    public JasperPrint ProfitReport(HashMap map) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/financialReport1.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport compileReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                compileReport,
                map,
                new JREmptyDataSource());
        JasperViewer.viewReport(jasperPrint, false);

        return jasperPrint;
    }
}
