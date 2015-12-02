package Model.ConsoleCommands;

import Model.LocalModel.LocalData;

/**
 * Created by Max on 02.12.2015.
 */
public class PrintSeats implements ICommand{
    private String name = "print";
    public void execute(int seatNumber, String lastName, String firstName) {
        new LocalData().read();
    }
    public void printHelp() {
        System.out.println("- " + name + " -- print all seats");
    }
}
