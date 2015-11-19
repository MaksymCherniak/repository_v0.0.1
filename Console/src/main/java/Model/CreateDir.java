package Model;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 18.11.2015.
 */
public class CreateDir implements Command{
    private String name = "mkdir";
    private File currentDirectory = new File(".");
    private File createdDirectory = new File(".");
    private String dirName = "";
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- create dir");
    }
    public void execute() {
    }

    public File execute(File currentDirectory) {
        return null;
    }

    public File execute(String args, File currentDirectory) {
        this.currentDirectory = currentDirectory;
        dirName = args;
        String createdDir = currentDirectory.getPath() + "/" + dirName;
        createdDirectory = new File(currentDirectory, dirName);
        createdDirectory.mkdir();
        System.out.println("Directory created \"" + createdDirectory.getPath() + "\"");
        System.out.println();
        return (File)currentDirectory;

    }
}
