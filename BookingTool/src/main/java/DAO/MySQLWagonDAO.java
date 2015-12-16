package DAO;

import Model.LocalModel.AvailabilitySeats;
import Model.LocalModel.JDBCDriver;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 16.12.2015.
 */
public class MySQLWagonDAO implements IWagonDAO{
    private Connection connection;
    public MySQLWagonDAO(){
        if (!wagonInitChecker()){
            insertWagon(new Wagon());
        }
    }
    public boolean insertSeat(int seatNumber, User user) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update wagon_1 set status='" + AvailabilitySeats.OCCUPIED  + "', " +
                    "ticket='" + user.getIndex() + "' where seat='" + seatNumber + "';");
            connection.close();

        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean insertWagon(Wagon wagon) {
        int[] seats = wagon.getSeats();
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < seats.length; i++) {
                statement.addBatch("INSERT INTO wagon_1 (seat, status) values (" + seats[i] + ", '" + AvailabilitySeats.FREE + "')");
            }
            statement.executeBatch();
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateWagon(String id) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update wagon_1 set status='" + AvailabilitySeats.FREE  + "', " +
                    "ticket=null where ticket='" + id + "';");
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List getAllSeats() {
        List<String> listOfSeats = new ArrayList<String>();
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from wagon_1");
            while (resultSet.next()){
                StringBuilder s = new StringBuilder();
                s.append("Seat number ").append(resultSet.getString("seat")).append(" is ")
                        .append(resultSet.getString("status"));
                if (resultSet.getString("ticket") != null) {
                    s.append(" by user with ").append(resultSet.getString("ticket")).append(" ID");
                }
                listOfSeats.add(String.valueOf(s));
            }
            connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return listOfSeats;
    }

    private boolean wagonInitChecker(){
        connection = JDBCDriver.getConnection();
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from wagon_1");
            while (resultSet.next()){
                count++;
            }
            if (count == 0){
                connection.close();
                return false;
            }else {
                connection.close();
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean checkSeatAvailable(int seatNumber) {
        connection = JDBCDriver.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select status from wagon_1 where seat='" + seatNumber + "'");
            while (resultSet.next()) {
                String s = resultSet.getString("status");
                if (s.equals(String.valueOf(AvailabilitySeats.FREE))){
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            }

        } catch (Exception e) {}
        return false;
    }
}
