package searcher.commands;

import searcher.entity.Folder;

import org.apache.log4j.Logger;

public class SetMonitoringPeriod implements ICommand {
    private static Logger log = Logger.getLogger(SetMonitoringPeriod.class.getName());
    private final static String name = "setmp";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);

        if (parts.length != 2) {
            log.warn(WRONG_COMMAND);
            return;
        }

        switch (parts[1]) {
            case DEFAULT:
                Folder.setDefaultMonitoringPeriod();
                break;
            default:
                Folder.setMonitoringPeriod(parts[1]);
                break;
        }

    }

    public String printHelp() {
        return "- " + name + " -- set folder for monitoring. \"- " + name + " \'folder url\' \"";
    }
}
