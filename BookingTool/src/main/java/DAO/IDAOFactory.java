package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDAOFactory {
    Connection getConnection() throws SQLException;

    IUserDAO getIUserDAO(Connection connection);

    IWagonDAO getIWagonDAO(Connection connection);
}