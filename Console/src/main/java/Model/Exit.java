package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class Exit implements ICommand {
    private String name = "exit";

    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- exit");
    }

    public File execute(String args, File currentDirectory) {
        System.exit(0);
        return currentDirectory;
    }
}
