package searcher.commands;

import searcher.entity.Folder;

import org.apache.log4j.Logger;

public class SetProcessedFilesFolder implements ICommand {
    private static Logger log = Logger.getLogger(SetProcessedFilesFolder.class.getName());
    private final static String name = "setpff";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);

        if (parts.length != 2) {
            log.warn(WRONG_COMMAND);
            return;
        }

        switch (parts[1]) {
            case DEFAULT:
                Folder.setDefaultProcessedFilesFolder();
                break;
            default:
                Folder.setProcessedFilesFolder(parts[1]);
                break;
        }
    }

    public String printHelp() {
        return "- " + name + " -- set processed files folder. \"- " + name + " \'folder url\' \"";
    }
}
