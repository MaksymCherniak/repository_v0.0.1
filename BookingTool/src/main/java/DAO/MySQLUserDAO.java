package DAO;

import Model.LocalModel.JDBCDriver;
import Model.LocalModel.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 16.12.2015.
 */
public class MySQLUserDAO implements IUserDAO{
    Connection connection;

    public int insertUser(User user) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into users (name, surname, ticket) values ('" + user.getFirstName() +
                    "', '" + user.getLastName() + "', " + user.getIndex() + ");");
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public void deleteUser(String id) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from users where ticket='" + id + "';");
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List getAllUsers() {
        List<String> listOfUsers = new ArrayList<String>();
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                StringBuilder s = new StringBuilder();
                s.append("ID ").append(resultSet.getString("ticket")).append(" Full name:  ")
                        .append(resultSet.getString("surname")).append(" ").append(resultSet.getString("name"));
                listOfUsers.add(String.valueOf(s));
            }
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return listOfUsers;
    }
}
