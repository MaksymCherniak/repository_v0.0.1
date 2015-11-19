package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class Exit implements Command{
    private String name = "exit";

    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- exit");
    }
    public void execute() {
        System.exit(0);
    }

    public File execute(String args, File currentDirectory) {
        return currentDirectory;
    }

    public File execute(File currentDirectory) {
        return null;
    }
}
