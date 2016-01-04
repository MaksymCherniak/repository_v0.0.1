package Model.ConsoleCommands;

import DAO.Factory;
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
        Ticket ticket = Factory.getMySQLTicketDAO().find(Integer.parseInt(id));
        if (ticket != null && ticket.getTrain() != null) {
            Factory.getInstance().getMySQLTicketDAO().delete(Integer.parseInt(id));
            Factory.getInstance().getMySQLWagonDAO().updateWagon(ticket);
        } else {
            log.warning("Model.LocalModel.User with id \"" + id + "\" not found.");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- remove user");
        System.out.println("     " + name + " \"id\"");
    }
}