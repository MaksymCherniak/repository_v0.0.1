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
 * Created by Max on 24.12.2015.
 */
public class MySQLTicketDAO implements ITicketDAO {
    private static final String TICKET = "ticket";
    private static Connection connection;

    public MySQLTicketDAO() {
        connection = JDBCDriver.getConnection();
    }

    public int insertTicket(Ticket ticket) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into " + TICKET + " (number, train, wagon, seat, user) values (" +
                    ticket.getNumber() + ", " + ticket.getTrain() + ", " + ticket.getWagon() + ", " +
                    ticket.getSeat() + ", " + ticket.getUser().getTicket() + ");");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Ticket find(int number) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + TICKET);
            while (resultSet.next()) {
                if (number == Integer.parseInt(resultSet.getString("number"))) {
                    Ticket ticket = new Ticket();
                    ticket.setNumber(Integer.parseInt(resultSet.getString("number")));
                    ticket.setWagon(Integer.parseInt(resultSet.getString("wagon")));
                    ticket.setSeat(Integer.parseInt(resultSet.getString("seat")));
                    ticket.setTrain(Integer.parseInt(resultSet.getString("train")));
                    User user = Factory.getInstance().getMySQLUserDAO().findUser(resultSet.getString("user"));
                    ticket.setUser(user);
                    return ticket;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(Ticket ticket) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from " + TICKET + " where number=" + ticket.getNumber() + ";");
            System.out.println("Ticket " + ticket.getNumber() + " removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> listOfTickets = new ArrayList<Ticket>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + TICKET);
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setNumber(Integer.parseInt(resultSet.getString("number")));
                ticket.setWagon(Integer.parseInt(resultSet.getString("wagon")));
                ticket.setSeat(Integer.parseInt(resultSet.getString("seat")));
                ticket.setTrain(Integer.parseInt(resultSet.getString("train")));
                User user = Factory.getInstance().getMySQLUserDAO().findUser(resultSet.getString("user"));
                ticket.setUser(user);
                listOfTickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfTickets;
    }

    public void setIndex() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + TICKET);
            Integer index = 0;
            while (resultSet.next()) {
                if (index < Integer.parseInt(resultSet.getString("number"))) {
                    index = Integer.parseInt(resultSet.getString("number"));
                }
            }
            Ticket.setIndex(index);
        } catch (Exception e) {
        }
    }
}
