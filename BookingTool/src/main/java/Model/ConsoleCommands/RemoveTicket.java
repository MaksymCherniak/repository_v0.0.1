package Model.ConsoleCommands;

import DAO.MySqlDaoFactory;
import Model.LocalModel.Ticket;

import java.util.logging.Logger;

public class RemoveTicket implements ICommand {
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
        Ticket ticket = MySqlDaoFactory.getMySQLTicketDAO().findTicketByID(Integer.parseInt(id));
        if (ticket != null && ticket.getTrain() != null) {
            MySqlDaoFactory.getInstance().getMySQLTicketDAO().delete(Integer.parseInt(id));
            MySqlDaoFactory.getInstance().getMySQLWagonDAO().updateWagon(ticket);
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- remove user");
        System.out.println("     " + name + " \"id\"");
    }
}