package searcher.commands;

import searcher.entity.Folder;

import org.apache.log4j.Logger;

public class SetFailedFolder implements ICommand {
    private static Logger log = Logger.getLogger(SetFailedFolder.class.getName());
    private final static String name = "setff";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);

        if (parts.length != 2) {
            log.warn(WRONG_COMMAND);
            return;
        }

        switch (parts[1]) {
            case DEFAULT:
                Folder.setDefaultFailedFolder();
                break;
            default:
                Folder.setFailedFolder(parts[1]);
                break;
        }

    }

    public String printHelp() {
        return "- " + name + " -- set folder for failed files. \"- " + name + " \'folder url\' \"";
    }
}
