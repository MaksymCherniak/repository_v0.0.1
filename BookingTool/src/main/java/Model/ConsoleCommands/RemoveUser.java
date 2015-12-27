package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Ticket;

/**
 * Created by Max on 08.12.2015.
 */
public class RemoveUser implements ICommand {
    private static String name = "remove";

    public void execute(int seatNumber, String lastName, String firstName) {
    }

    public void remove(String id) {
        Ticket ticket = Factory.getMySQLTicketDAO().find(Integer.parseInt(id));
        if (ticket != null && ticket.getNumber() != null) {
            Factory.getMySQLTicketDAO().delete(ticket);
            Factory.getMySQLWagonDAO().updateWagon(ticket);
            Factory.getInstance().getMySQLUserDAO().deleteUser(ticket.getUser());
        } else {
            System.out.println("User with id \"" + id + "\" not found.");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- remove user from xml file");
        System.out.println("     " + name + " \"id\"");
    }
}
