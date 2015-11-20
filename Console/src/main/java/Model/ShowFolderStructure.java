package Model;

import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Max on 18.11.2015.
 */
public class ShowFolderStructure implements ICommand {
    private TreeSet<String> set = new TreeSet<String>();
    private String name = "tree";
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- show folder structure");
    }

    public File execute(String args, File currentDirectory) {
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        execute(args, temp);
                    }
                }
            }
        }
        return currentDirectory;
    }
}
