package DAO;

public class Factory {
    private static MySQLUserDAO mySQLUserDAO = null;
    private static MySQLWagonDAO mySQLWagonDAO = null;
    private static MySQLTicketDAO mySQLTicketDAO = null;
    private static Factory instance = null;
    private static MySQLRouteDAO mySQLRouteDAO = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public static MySQLRouteDAO getMySQLRouteDAO() {
        if (mySQLRouteDAO == null) {
            mySQLRouteDAO = new MySQLRouteDAO();
        }
        return mySQLRouteDAO;
    }

    public static MySQLTicketDAO getMySQLTicketDAO() {
        if (mySQLTicketDAO == null) {
            mySQLTicketDAO = new MySQLTicketDAO();
        }
        return mySQLTicketDAO;
    }

    public static MySQLUserDAO getMySQLUserDAO() {
        if (mySQLUserDAO == null) {
            mySQLUserDAO = new MySQLUserDAO();
        }
        return mySQLUserDAO;
    }

    public static MySQLWagonDAO getMySQLWagonDAO() {
        if (mySQLWagonDAO == null) {
            mySQLWagonDAO = new MySQLWagonDAO();
        }
        return mySQLWagonDAO;
    }
}