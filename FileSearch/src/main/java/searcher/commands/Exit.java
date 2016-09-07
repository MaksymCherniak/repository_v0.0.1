package searcher.commands;

import org.apache.log4j.Logger;

public class Exit implements ICommand {
    private static Logger log = Logger.getLogger(Exit.class.getName());
    private final static String name = "exit";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);
        if (parts.length == 1) {
            System.exit(0);
        } else {
            log.warn(WRONG_COMMAND);
        }
    }

    public String printHelp() {
        return "- " + name + " -- exit from  system";
    }
}