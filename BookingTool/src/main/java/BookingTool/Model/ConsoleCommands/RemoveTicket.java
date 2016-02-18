package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.Service.ITicketDAO;
import BookingTool.DAO.Service.IWagonDAO;
import BookingTool.Model.LocalModel.Ticket;

import java.util.logging.Logger;

public class RemoveTicket implements ICommand {
    private ITicketDAO iTicketDAO;
    private IWagonDAO iWagonDAO;
    private static Logger log = Logger.getLogger(RemoveTicket.class.getName());
    private final static String name = "remove";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 2) {
            remove(parts[1]);
        } else {
            log.warning("Wrong command");
        }
    }

    public void remove(String id) {
        Ticket ticket = iTicketDAO.findTicketByID(Long.parseLong(id));
        if (ticket != null && ticket.getTrain() != null) {
            iTicketDAO.delete(Long.parseLong(id));
            iWagonDAO.updateWagon(ticket);
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- remove user.");
        System.out.println("     " + name + " \"id\"");
    }

    public ITicketDAO getiTicketDAO() {
        return iTicketDAO;
    }

    public IWagonDAO getiWagonDAO() {
        return iWagonDAO;
    }

    public void setiTicketDAO(ITicketDAO iTicketDAO) {
        this.iTicketDAO = iTicketDAO;
    }

    public void setiWagonDAO(IWagonDAO iWagonDAO) {
        this.iWagonDAO = iWagonDAO;
    }
}