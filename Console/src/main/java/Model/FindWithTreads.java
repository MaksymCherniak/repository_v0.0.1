package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 21.11.2015.
 */
public class FindWithTreads extends Thread implements ICommand{
    private static String name = "fnd";
    private String[] parts;
    private static final int DEFAULT_QUANTITY_THREADS = 50;
    private static int quantityTreads;
    private String fileName;
    private File currentDirectory;
    public FindWithTreads(){}
    public FindWithTreads(String fileName, File currentDirectory) {
        this.fileName = fileName;
        this.currentDirectory = currentDirectory;
    }
    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void printHelp() {
        System.out.println("-" + name + " \"name\" -- find file using threads, with quantity of threads by default");
        System.out.println("-" + name + " \"name\" N -- find file using threads, N - quantity of threads");}

    public File execute(String args, File currentDirectory) {
        if (checkParts(args)) {
            fileName = args;
            quantityTreads = DEFAULT_QUANTITY_THREADS;
        } else {
            fileName = parts[0];
            quantityTreads = Integer.parseInt(parts[1]);
        }
        this.currentDirectory = currentDirectory;
        start();
        return currentDirectory;
    }

    @Override
    public void run() {
        Counter.incCountOfThreads();
        //System.out.println(Thread.currentThread());
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    //System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        if (Counter.countOfThreads <= quantityTreads) {
                            new FindWithTreads(fileName, temp).start();
                        } else try {
                            Counter.decCountOfThreads();
                            Thread.sleep(100);
                            new FindWithTreads(fileName, currentDirectory).start();
                            //System.out.println(Thread.currentThread() + " fin");
                            stop();

                        } catch (InterruptedException e) {}
                    } else if (temp.getName().equals(fileName)) {
                        System.out.println(temp.getAbsoluteFile().toString() + " found");
                    }
                }
            }
        }
        Counter.decCountOfThreads();
        //System.out.println(Thread.currentThread() + " finished");
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
class Counter{
    protected static volatile int countOfThreads = 0;
    protected static synchronized void incCountOfThreads() { countOfThreads++; }
    protected static synchronized void decCountOfThreads() { countOfThreads--; }
}