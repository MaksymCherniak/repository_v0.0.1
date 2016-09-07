package searcher.commands;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

public class Help implements ICommand {
    private static Logger log = Logger.getLogger(Help.class.getName());
    private static Set<String> commands;
    private final static String name = "help";
    private String[] parts;

    static {
        commands = new HashSet<String>();
        commands.add(new Help().printHelp());
        commands.add(new Exit().printHelp());
        commands.add(new SetProcessedFilesFolder().printHelp());
        commands.add(new SetStartFolder().printHelp());
        commands.add(new SetFailedFolder().printHelp());
        commands.add(new SetMonitoringPeriod().printHelp());
        commands.add(new Start().printHelp());
        commands.add(new Print().printHelp());
    }

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);

        if (parts.length == 1) {
            for (String cmnd : commands) {
                System.out.println(cmnd);
            }
        } else {
            log.warn(WRONG_COMMAND);
        }
    }

    public String printHelp() {
        return "- " + name + " -- print all available commands";
    }
}