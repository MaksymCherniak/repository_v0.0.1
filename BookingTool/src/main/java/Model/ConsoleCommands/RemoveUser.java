package Model.ConsoleCommands;

import DAO.*;
import Model.LocalModel.Ticket;
import Model.LocalModel.User;

/**
 * Created by Max on 08.12.2015.
 */
public class RemoveUser implements ICommand {
    private static String name = "remove";
    public void execute(int seatNumber, String lastName, String firstName) { }
    public void remove(String id){
        Ticket ticket = Factory.getInstance().getMySQLTicketDAO().find(Integer.parseInt(id));
        if (ticket != null && ticket.getNumber() != null) {
            Factory.getInstance().getMySQLTicketDAO().delete(ticket);
            Factory.getInstance().getMySQLWagonDAO().updateWagon(ticket);
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
