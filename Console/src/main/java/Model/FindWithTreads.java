package Model;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 21.11.2015.
 */
public class FindWithTreads implements ICommand{
    private static String name = "fnd";
    private String[] parts;
    private final int DEFAULT_QUANTITY_THREADS = 20;
    private int quantityTreads;
    private MyThread myThread;
    public String getName() { return name; }

    public void printHelp() { System.out.println("-" + name + " -- find file with threads"); }

    public File execute(String args, File currentDirectory) {
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        myThread = new MyThread(args, temp);
                        myThread.start();
                        System.out.println(Thread.currentThread());
                       // execute(args, temp);
                    }
                    else if (args.equals(temp.getName())) {
                        System.out.println(temp.getAbsoluteFile().toString() + " found");
                    }
                }
            }
        }
        return currentDirectory;
    }



    public boolean checkParts(String fullLine)
    {
        if (fullLine.contains(" "))
        {
            parts = fullLine.split(" ");
            return false;
        }else {
            return true;
        }
    }
}
