package BookingTool.Model.ConsoleCommands;

import java.util.logging.Logger;

public class Exit implements ICommand {
    private static Logger log = Logger.getLogger(Exit.class.getName());
    private final static String name = "exit";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            System.exit(0);
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- exit from system");
    }
}