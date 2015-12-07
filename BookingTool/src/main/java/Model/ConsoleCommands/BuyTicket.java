package Model.ConsoleCommands;

import Model.LocalModel.LocalData;
import Model.LocalModel.User;

/**
 * Created by Max on 02.12.2015.
 */
public class BuyTicket implements ICommand {
    private static String name = "buy";

    public void execute(int seatNumber, String lastName, String firstName) {
        new LocalData().update(seatNumber, new User().create(lastName, firstName));
        System.out.println("Thanks for your order. Your seat is number " + seatNumber + ".");
    }

    public void printHelp() {
        System.out.println("- " + name + " -- buy some ticket.");
        System.out.println("     " + name + " \"seatNumber\" \"Your surname\" \"Your name\"");
    }
}
