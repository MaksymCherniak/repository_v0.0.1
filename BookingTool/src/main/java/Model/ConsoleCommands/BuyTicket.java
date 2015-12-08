package Model.ConsoleCommands;

import Model.LocalModel.LocalData;
import Model.LocalModel.User;
import Model.LocalModel.XmlUserDAO;

/**
 * Created by Max on 02.12.2015.
 */
public class BuyTicket implements ICommand {
    private static String name = "buy";

    public void execute(int seatNumber, String lastName, String firstName) {
        User user = new User(lastName, firstName);
        new LocalData().update(seatNumber, user);
        new XmlUserDAO().insertUser(user);
        System.out.println("Thanks for your order. Your seat is number " + seatNumber + ".");
    }

    public void printHelp() {
        System.out.println("- " + name + " -- buy some ticket.");
        System.out.println("     " + name + " \"seatNumber\" \"Your surname\" \"Your name\"");
    }
}
