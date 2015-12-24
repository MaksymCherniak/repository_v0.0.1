package DAO;

import Model.LocalModel.JDBCDriver;
import Model.LocalModel.Ticket;
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
    public MySQLUserDAO(){
        connection = JDBCDriver.getConnection();
    }
    public int insertUser(User user) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into " + USERS + " (id, " + NAME + ", " + SURNAME + ", " + TICKET + ") values ('"
                    + user.getIndex() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', " + user.getTicket() + ");");
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public User findUser(String id) {
        User user = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + USERS);
            while (resultSet.next()){
                String ticket = resultSet.getString(TICKET);
                if (ticket.equals(id)) {
                    user.setFirstName(resultSet.getString(NAME));
                    user.setLastName(resultSet.getString(SURNAME));
                    user.setTicket(Integer.parseInt(ticket));
                    return user;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void deleteUser(User user) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from " + USERS +" where " + TICKET + "='" + user.getTicket() + "';");
            System.out.println("User with ticket number " + user.getTicket() + " removed.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List getAllUsers() {
        List<User> listOfUsers = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + USERS);
            while (resultSet.next()){
                User user = new User();
                user.setTicket(Integer.parseInt(resultSet.getString(TICKET)));
                user.setFirstName(resultSet.getString(NAME));
                user.setLastName(resultSet.getString(SURNAME));
                listOfUsers.add(user);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return listOfUsers;
    }
    public void setIndex(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + USERS);
            Integer index = 0;
            while (resultSet.next()){
                if (index < Integer.parseInt(resultSet.getString(TICKET))){
                    index = Integer.parseInt(resultSet.getString(TICKET));
                }
            }
            User.setIndex(index);
        }catch (Exception e){}
    }
}
