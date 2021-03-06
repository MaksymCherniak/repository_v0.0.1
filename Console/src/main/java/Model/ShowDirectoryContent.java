package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class ShowDirectoryContent implements ICommand {
    private String name = "dir";

    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- show directory content");
    }

    public File execute(String args, File currentDirectory) {
        System.out.println("Content at \"" + currentDirectory.getPath() + "\":");
        System.out.println();
        for (String s : currentDirectory.list())
        {
            System.out.println(s);
        }
        System.out.println();
        return currentDirectory;
    }
}
