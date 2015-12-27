package Model.ConsoleCommands;

/**
 * Created by Max on 02.12.2015.
 */
public class Exit implements ICommand {
    private static String name = "exit";

    public void execute(int seatNumber, String lastName, String firstName) {
        System.exit(0);
    }

    public void printHelp() {
        System.out.println("- " + name + " -- exit from system");
    }
}
