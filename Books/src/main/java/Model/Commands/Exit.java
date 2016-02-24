package Model.Commands;

import java.util.logging.Logger;

public class Exit implements ICommand {
    private static Logger log = Logger.getLogger(Exit.class.getName());
    private static final String name = "exit";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            System.exit(0);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- exit from  system");
    }
}
