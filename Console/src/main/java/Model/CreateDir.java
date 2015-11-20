package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class CreateDir implements ICommand {
    private String name = "mkdir";
    private File createdDirectory = new File(".");
    private String dirName;
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- create dir");
    }

    public File execute(String args, File currentDirectory) {
        if (args == null)
        {
            System.out.println("Wrong command.");
            return currentDirectory;
        }
        dirName = args;
        createdDirectory = new File(currentDirectory, dirName);
        if (!createdDirectory.exists()) {
            createdDirectory.mkdir();
            System.out.println("Directory created \"" + createdDirectory.getPath() + "\"");
            System.out.println();
        }else{
            System.out.println("Каталог \"" + createdDirectory.getPath() + "\" уже существует.");
            System.out.println();
        }
        return currentDirectory;
    }
}
