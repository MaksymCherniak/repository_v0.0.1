package Model;

import View.ConsoleView;
import sun.misc.Cache;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Max on 21.11.2015.
 */
public class FindWithTreads extends Thread implements ICommand{
    private static volatile Object object;
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
        object = new Object();
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
        synchronized (object) {
            incCountOfThreads();
            if (!(countOfThreads <= quantityTreads)) {
                System.out.println(Thread.currentThread() + " Waiting");
                try {
                    object.wait();
                } catch (InterruptedException e) {}
                //System.out.println(Thread.currentThread() + " end waiting");
            }
        }
            System.out.println(Thread.currentThread());
            if (currentDirectory.isDirectory()) {
                if (currentDirectory.canRead()) {
                    for (File temp : currentDirectory.listFiles()) {
                        System.out.println(temp.getPath());
                        if (temp.isDirectory()) {
                            if (countOfThreads <= quantityTreads) {
                                new FindWithTreads(fileName, temp).start();
                            }
                        } else if (temp.getName().equals(fileName)) {
                            System.out.println(temp.getAbsoluteFile().toString() + " found");
                        }
                    }
                }
            } else {
                if (currentDirectory.getName().equals(fileName)) {
                    System.out.println(currentDirectory.getAbsoluteFile().toString() + " found");
                }
            }
        synchronized (object) {
            decCountOfThreads();
            System.out.println(Thread.currentThread() + " finished");
            if ((countOfThreads <= quantityTreads)){
                object.notify();
            }
        }
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
    protected static volatile int countOfThreads = 0;
    protected static synchronized void incCountOfThreads() { countOfThreads++; }
    protected static synchronized void decCountOfThreads() { countOfThreads--; }
}
