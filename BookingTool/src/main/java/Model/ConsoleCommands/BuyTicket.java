package Model.ConsoleCommands;

import DAO.Factory;
import DAO.MySQLWagonDAO;
import Model.LocalModel.Ticket;
import Model.LocalModel.User;

import java.util.logging.Logger;


public class BuyTicket implements ICommand {
    private static Logger log = Logger.getLogger(BuyTicket.class.getName());
    private final static String name = "buy";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 5){
            buyTicket(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3], parts[4]);
        } else {
            log.warning("Wrong command");
        }
    }

    public void buyTicket(int wagonNumber, int seatNumber, String lastName, String firstName) {
        MySQLWagonDAO mySQLWagonDAO = Factory.getMySQLWagonDAO();
        Ticket ticket = new Ticket();
        ticket.setWagon(mySQLWagonDAO.findWagon(wagonNumber));
        ticket.setSeat(seatNumber);
        ticket.setTrain(567);
        User user = new User(lastName, firstName);
        if (mySQLWagonDAO.checkSeatAvailable(ticket)) {
            Factory.getInstance().getMySQLUserDAO().insertUser(user);
            ticket.setUser(user);
            Factory.getMySQLTicketDAO().insertTicket(ticket);
            mySQLWagonDAO.updateSeat(ticket);
            log.info("Thanks for your order. Your seat is number " + seatNumber + ", Ticket number: " + ticket.getIndex());
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- buy some ticket.");
        System.out.println("     " + name + " \"seatNumber\" \"Your surname\" \"Your name\"");
    }
}