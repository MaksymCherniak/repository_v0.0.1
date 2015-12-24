package DAO;

/**
 * Created by Max on 20.12.2015.
 */
public class Factory {
    private static UserDAOImpl userDAO = null;
    private static MySQLUserDAO mySQLUserDAO = null;
    private static MySQLWagonDAO mySQLWagonDAO = null;
    private static MySQLTicketDAO mySQLTicketDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public static MySQLTicketDAO getMySQLTicketDAO() {
        if (mySQLTicketDAO == null){
            mySQLTicketDAO = new MySQLTicketDAO();
        }
        return mySQLTicketDAO;
    }

    public UserDAOImpl getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
    public MySQLUserDAO getMySQLUserDAO(){
        if (mySQLUserDAO == null){
            mySQLUserDAO = new MySQLUserDAO();
        }
        return mySQLUserDAO;
    }

    public static MySQLWagonDAO getMySQLWagonDAO() {
        if (mySQLWagonDAO == null){
            mySQLWagonDAO = new MySQLWagonDAO();
        }
        return mySQLWagonDAO;
    }

}
