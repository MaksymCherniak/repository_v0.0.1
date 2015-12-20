package DAO;

/**
 * Created by Max on 20.12.2015.
 */
public class Factory {
    private static UserDAOImpl userDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public UserDAOImpl getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
}
