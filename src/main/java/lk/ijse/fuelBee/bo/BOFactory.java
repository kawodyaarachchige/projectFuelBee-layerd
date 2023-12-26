package lk.ijse.fuelBee.bo;

import lk.ijse.fuelBee.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return( boFactory == null) ? boFactory = new BOFactory() : boFactory;

    }
    public enum BOType{
        ADMIN, EMPLOYEE, FUEL ,INCOME,MACHINE,ORDER,OUTCOME,PAYMENT,SUPPLIER,TANK,
    }
    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case ADMIN:
                return new AdminBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case FUEL:
                return new FuelBOImpl();
            case INCOME:
                return new IncomeBOImpl();
            case MACHINE:
                return new MachineBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case OUTCOME:
                return new OutcomeBOImpl();
            case PAYMENT:
                return new PaymentsBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case TANK:
                return new TankBOImpl();
            default:
                return null;
        }
    }
}
