package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.SQLException;
import java.util.HashMap;

public interface ReportsBO extends SuperBO {
    JasperPrint SalaryReport() throws JRException, SQLException;
    JasperPrint paymentReport() throws JRException, SQLException;
    JasperPrint ProfitReport(HashMap map) throws JRException, SQLException;
}
