package Model.ConsoleCommands;

/**
 * Created by Max on 02.12.2015.
 */
public class Help implements ICommand {
    private static String name = "help";

    public void execute(int seatNumber, String lastName, String firstName) {
        new Help().printHelp();
        new BuyTicket().printHelp();
        new Exit().printHelp();
        new RemoveUser().printHelp();
        new PrintAllUsersFromDatabase().printHelp();
        new InsertWagon().printHelp();
        new PrintWagon().printHelp();
        new PrintAllWagons().printHelp();
        new PrintAllTickets().printHelp();
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all available commands");
    }
}
