package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class ChangeCurrentLocation implements Command {
    private static String name = "cd";
    private File currentDirectory;
    public String getName() {
        return name;
    }
    public void printHelp() {
        System.out.println("-" + name + " -- change current location");
    }
    public void execute() {    }
    public File execute(String args, File currentDirectory) {
        String s = currentDirectory.getPath() + args;
        this.currentDirectory = new File(currentDirectory, args);
        System.out.println(this.currentDirectory.getPath());
        System.out.println();
        return this.currentDirectory;
    }

    public File execute(File currentDirectory) {
        this.currentDirectory = new File(currentDirectory.getParent());
        System.out.println(this.currentDirectory.getPath());
        System.out.println();
        return this.currentDirectory;
    }
}
