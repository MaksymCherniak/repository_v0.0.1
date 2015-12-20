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
    private final String USERS = "users";
    private final String NAME = "name";
    private final String SURNAME = "surname";
    private final String TICKET = "ticket";

    public int insertUser(User user) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into " + USERS + " (" + NAME + ", " + SURNAME + ", " + TICKET + ") values ('" +
                    user.getFirstName() + "', '" + user.getLastName() + "', " + user.getIndex() + ");");
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public User findUser(String id) {
        connection = JDBCDriver.getConnection();
        User user = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + USERS);
            while (resultSet.next()){
                String ticket = resultSet.getString(TICKET);
                if (ticket.equals(id)) {
                    String name = resultSet.getString(NAME);
                    String surname = resultSet.getString(SURNAME);
                    user.setFirstName(name);
                    user.setLastName(surname);
                    user.setTicket(ticket);
                    break;
                }
            }
            connection.close();
            return user;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void deleteUser(User user) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from " + USERS +" where " + TICKET + "='" + user.getTicket() + "';");
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List getAllUsers() {
        List<String> listOfUsers = new ArrayList<String>();
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + USERS);
            while (resultSet.next()){
                StringBuilder s = new StringBuilder();
                s.append("ID ").append(resultSet.getString(TICKET)).append(" Full name:  ")
                        .append(resultSet.getString(SURNAME)).append(" ").append(resultSet.getString(NAME));
                listOfUsers.add(String.valueOf(s));
            }
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return listOfUsers;
    }
}
