package DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Max on 01.12.2015.
 */
public interface IDAOFactory {
    Connection getConnection() throws SQLException;

    IUserDAO getIUserDAO(Connection connection);

    IWagonDAO getIWagonDAO(Connection connection);
}
