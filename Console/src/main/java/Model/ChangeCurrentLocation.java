package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class ChangeCurrentLocation implements ICommand {
    private static String name = "cd";
    private static String name2 = "cd \"name\"";
    private File currentDirectory;
    public String getName() {
        return name;
    }
    public void printHelp() {
        System.out.println("-" + name + " -- go to parent directory");
        System.out.println("-" + name2 + " -- go to directory \"name\"");
    }

    public File execute(String args, File currentDirectory) {
        if (args != null) {
            this.currentDirectory = new File(currentDirectory, args);
            System.out.println(this.currentDirectory.getPath());
            System.out.println();
            return this.currentDirectory;
        }else {
            this.currentDirectory = new File(currentDirectory.getParent());
            System.out.println(this.currentDirectory.getPath());
            System.out.println();
            return this.currentDirectory;
        }
    }
}
