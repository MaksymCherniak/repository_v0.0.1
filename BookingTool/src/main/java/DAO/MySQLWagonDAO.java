package DAO;

import Model.LocalModel.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 16.12.2015.
 */
public class MySQLWagonDAO implements IWagonDAO {
    private Connection connection;

    public MySQLWagonDAO() {
        connection = JDBCDriver.getConnection();
    }

    public boolean insertSeat(Ticket ticket) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update wagon_" + ticket.getWagon() + " set status='" + AvailabilitySeats.OCCUPIED + "', " +
                    "ticket='" + ticket.getIndex() + "' where seat='" + ticket.getSeat() + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertWagon(Wagon wagon) {
        List<Integer> seats = new ArrayList<>();
        Integer wagonNumber = wagon.getNumber();
        if (String.valueOf(wagon.getWagonType()).equalsIgnoreCase("COMFORTABLE")) {
            seats = Wagon.getComfortableWagonSeats();
        } else if (String.valueOf(wagon.getWagonType()).equalsIgnoreCase("COMPARTMENT")) {
            seats = Wagon.getCompartmentWagonList();
        } else if (String.valueOf(wagon.getWagonType()).equalsIgnoreCase("ECONOMY")) {
            seats = Wagon.getEconomyWagonList();
        }
        if (!wagonInitChecker(wagonNumber)) {
            try {
                Statement statement = connection.createStatement();
                for (Integer seat : seats) {
                    statement.addBatch("INSERT INTO wagon_" + wagonNumber + " (seat, status) values (" +
                            (seat + 1) + ", '" + AvailabilitySeats.FREE + "')");
                }
                statement.executeBatch();
                System.out.println("Wagon " + wagonNumber + " is initialize");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Wagon " + wagonNumber + " already initialized.");
        }
        return false;
    }

    public boolean updateWagon(Ticket ticket) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update wagon_" + ticket.getWagon() + " set status='" + AvailabilitySeats.FREE + "', " +
                    "ticket=null where ticket='" + ticket.getNumber() + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Seat> getAllSeats(int wagonNumber) {
        List<Seat> listOfSeats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from wagon_" + wagonNumber);
            while (resultSet.next()) {
                Seat seat = new Seat();
                seat.setNumber(Integer.parseInt(resultSet.getString("seat")));
                seat.setStatus(resultSet.getString("status"));
                if (resultSet.getString("ticket") != null) {
                    seat.setTicket(resultSet.getString("ticket"));
                }
                listOfSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSeats;
    }

    public List<Wagon> getAllWagons() {
        List<Wagon> listOfWagons = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from train");
            while (resultSet.next()) {
                Wagon wagon = new Wagon();
                wagon.setNumber(Integer.parseInt(resultSet.getString("wagon")));
                wagon.setWagonType(resultSet.getString("type"));
                wagon.setFreeSeats(getFreeSeats(Integer.parseInt(resultSet.getString("wagon"))));
                listOfWagons.add(wagon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfWagons;
    }

    private int getFreeSeats(int wagonNumber) {
        List<Seat> listOfSeats = getAllSeats(wagonNumber);
        int freeSeats = 0;
        for (Seat seat : listOfSeats) {
            if (String.valueOf(seat.getStatus()).equalsIgnoreCase("free")) {
                freeSeats++;
            }
        }
        return freeSeats;
    }

    private boolean wagonInitChecker(int wagonNumber) {
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from wagon_" + wagonNumber);
            while (resultSet.next()) {
                count++;
            }
            return count != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkSeatAvailable(Ticket ticket) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select status from wagon_" + ticket.getWagon() + " where seat='" + ticket.getSeat() + "'");
            resultSet.next();
            String s = resultSet.getString("status");
            return s.equals(String.valueOf(AvailabilitySeats.FREE));

        } catch (Exception e) {
        }
        return false;
    }
}
