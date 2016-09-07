package searcher.commands;

import searcher.entity.Folder;

import org.apache.log4j.Logger;

public class Print implements ICommand {
    private static Logger log = Logger.getLogger(Print.class.getName());
    private final static String name = "print";
    private String[] parts;

    @Override
    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);
        if (parts.length != 2) {
            log.warn(WRONG_COMMAND);
            return;
        }
        switch (parts[1]) {
            case "mp":
                System.out.println(Folder.getMonitoringPeriod());
                break;
            case "sf":
                System.out.println(Folder.getStartFolder().getAbsoluteFile());
                break;
            case "ff":
                System.out.println(Folder.getFailedFolder().getAbsolutePath());
                break;
            case "pff":
                System.out.println(Folder.getProcessedFilesFolder().getAbsolutePath());
                break;
            default:
                log.warn(WRONG_COMMAND);
        }
    }

    @Override
    public String printHelp() {
        return "- " + name + " -- print info (\"mp\" - monitoring period, \"sf\" - start folder, " +
                "\n\"ff\" - failed folder, \"pff\" - processed files folder)";
    }
}
