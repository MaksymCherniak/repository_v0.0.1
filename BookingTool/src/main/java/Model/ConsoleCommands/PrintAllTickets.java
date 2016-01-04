package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Ticket;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllTickets implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllTickets.class.getName());
    private final static String name = "printt";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<Ticket> list = Factory.getMySQLTicketDAO().getAllTickets();
            for (Ticket ticket : list) {
                System.out.println(ticket.toString());
            }
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all tickets");
    }
}