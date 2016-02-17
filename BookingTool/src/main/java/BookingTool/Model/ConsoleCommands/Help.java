package BookingTool.Model.ConsoleCommands;

import java.util.logging.Logger;

public class Help implements ICommand {
    private static Logger log = Logger.getLogger(Help.class.getName());
    private final static String name = "help";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");

        if (parts.length == 1) {
            new Help().printHelp();
            new BuyTicket().printHelp();
            new Exit().printHelp();
            new RemoveTicket().printHelp();
            new PrintAllUsers().printHelp();
            new InsertWagon().printHelp();
            new PrintWagon().printHelp();
            new PrintAllWagons().printHelp();
            new PrintAllTickets().printHelp();
            new CreateUser().printHelp();
            new PrintRoute().printHelp();
            new InsertRoute().printHelp();
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all available commands");
    }
}