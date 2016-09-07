package searcher.commands;

import searcher.entity.Folder;

import org.apache.log4j.Logger;

public class SetStartFolder implements ICommand {
    private static Logger log = Logger.getLogger(SetStartFolder.class.getName());
    private final static String name = "setsf";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);

        if (parts.length != 2) {
            log.warn(WRONG_COMMAND);
            return;
        }

        switch (parts[1]) {
            case DEFAULT:
                Folder.setDefaultStartFolder();
                break;
            default:
                Folder.setStartFolder(parts[1]);
                break;
        }
    }

    public String printHelp() {
        return "- " + name + " -- set folder for monitoring. \"- " + name + " \'folder url\' \"";
    }
}
