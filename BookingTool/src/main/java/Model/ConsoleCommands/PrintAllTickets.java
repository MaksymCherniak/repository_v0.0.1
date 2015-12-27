package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Ticket;

import java.util.List;

/**
 * Created by Max on 24.12.2015.
 */
public class PrintAllTickets implements ICommand {
    private static String name = "printt";

    public void execute(int seatNumber, String lastName, String firstName) {
        List<Ticket> list = Factory.getMySQLTicketDAO().getAllTickets();
        for (Ticket ticket : list) {
            System.out.println(ticket.toString());
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all tickets");
    }
}
