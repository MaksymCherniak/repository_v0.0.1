package Model.ConsoleCommands;

import DAO.MySqlDaoFactory;
import DAO.MySQLWagonDAO;
import Model.LocalModel.Ticket;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;

import java.util.logging.Logger;

public class BuyTicket implements ICommand {
    private static Logger log = Logger.getLogger(BuyTicket.class.getName());
    private final static String name = "buy";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 6) {
            buyTicket(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4], parts[5]);
        } else {
            log.warning("Wrong command");
        }
    }

    public boolean buyTicket(int trainNumber, int wagonNumber, int seatNumber, String lastName, String firstName) {
        MySQLWagonDAO mySQLWagonDAO = MySqlDaoFactory.getMySQLWagonDAO();
        Wagon wagon = mySQLWagonDAO.findWagon(wagonNumber);
        if (wagon != null) {
            Ticket ticket = new Ticket();
            ticket.setWagon(wagon);
            ticket.setSeat(seatNumber);
            ticket.setTrain(trainNumber);
            User user = new User(lastName, firstName);
            if (mySQLWagonDAO.checkSeatAvailable(ticket)) {
                MySqlDaoFactory.getMySQLUserDAO().insertUser(user);
                ticket.setUser(user);
                MySqlDaoFactory.getMySQLTicketDAO().insertTicket(ticket);
                mySQLWagonDAO.updateSeat(ticket);
                log.info("Thanks for your order. Your seat is number " + seatNumber + ", Ticket number: " + ticket.getIndex());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- buy some ticket.");
        System.out.println("     " + name + " \"Train number\" \"Wagon number\" \"Seat number\" \"Your surname\" \"Your name\"");
    }
}