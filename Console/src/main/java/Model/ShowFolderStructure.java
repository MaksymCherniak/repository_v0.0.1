package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class ShowFolderStructure implements Command {
    private String name = "tree";
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- show folder structure");
    }
    public void execute() {

    }

    public File execute(String args, File currentDirectory) {
        return currentDirectory;
    }

    public File execute(File currentDirectory) {
        System.out.println("Currend directory: ");
        System.out.println(currentDirectory.getPath());
        System.out.println();
        return currentDirectory;
    }
}
