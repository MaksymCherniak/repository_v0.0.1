package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 18.11.2015.
 */
public class Find implements ICommand {
    private static List<String> result = new ArrayList<String>();
    private String name = "find";
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- find file ( dir )");
    }

    public File execute(String args, File currentDirectory) {
        find(args, currentDirectory);
        if (result.size() == 0) {
            System.out.println("File not found.");
        }else {
            for (String s : result) {
                System.out.println(s);
            }
        }
        return currentDirectory;
    }
    public void find(String args, File currentDirectory)
    {
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        find(args, temp);
                    }
                    else if (args.equals(temp.getName())) {
                        result.add(temp.getAbsoluteFile().toString() + " found");
                    }
                }
            }
        }
    }
}
