package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.ITicketDAO;
import BookingTool.DAO.IUserDAO;
import BookingTool.DAO.IWagonDAO;
import BookingTool.Model.LocalModel.Ticket;
import BookingTool.Model.LocalModel.User;
import BookingTool.Model.LocalModel.Wagon;

import java.util.logging.Logger;

public class BuyTicket implements ICommand {
    private IWagonDAO iWagonDAO;
    private IUserDAO iUserDAO;
    private ITicketDAO iTicketDAO;
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
        Wagon wagon = iWagonDAO.findWagon(wagonNumber);
        if (wagon != null) {
            Ticket ticket = new Ticket();
            ticket.setWagon(wagon);
            ticket.setSeat(seatNumber);
            ticket.setTrain(trainNumber);
            User user = new User(lastName, firstName);
            if (iWagonDAO.checkSeatAvailable(ticket)) {
                iUserDAO.insertUser(user);
                ticket.setUser(user);
                iTicketDAO.insertTicket(ticket);
                iWagonDAO.updateSeat(ticket);
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

    public IWagonDAO getiWagonDAO() {
        return iWagonDAO;
    }

    public IUserDAO getiUserDAO() {
        return iUserDAO;
    }

    public ITicketDAO getiTicketDAO() {
        return iTicketDAO;
    }

    public void setiWagonDAO(IWagonDAO iWagonDAO) {
        this.iWagonDAO = iWagonDAO;
    }

    public void setiUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    public void setiTicketDAO(ITicketDAO iTicketDAO) {
        this.iTicketDAO = iTicketDAO;
    }
}