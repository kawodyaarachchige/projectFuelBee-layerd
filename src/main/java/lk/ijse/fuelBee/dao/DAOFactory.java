package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType {
        ADMIN, EMPLOYEE, FUEL, INCOME, MACHINE, ORDER, OUTCOME, PAYMENTS, SUPPLIER, TANK, QUERY

    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case ADMIN:
                return new AdminDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case FUEL:
                return new FuelDAOImpl();
            case INCOME:
                return new IncomeDAOImpl();
            case MACHINE:
                return new MachineDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case OUTCOME:
                return new OutcomeDAOImpl();
            case PAYMENTS:
                return new PaymentsDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TANK:
                return new TankDAOImpl();
            default:
                return null;

        }
    }
}