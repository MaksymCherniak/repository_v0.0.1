package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class Find implements ICommand {
    private String name = "find";
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- find file ( dir )");
    }

    public File execute(String args, File currentDirectory) {
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        execute(args, temp);
                    }
                    else if (args.equals(temp.getName())) {
                        System.out.println(temp.getAbsoluteFile().toString() + " found");
                    }
                }
            }
        }
        return currentDirectory;
    }
}
