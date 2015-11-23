package Model;

import View.ConsoleView;
import sun.misc.Cache;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Max on 21.11.2015.
 */
public class FindWithTreads extends Thread implements ICommand{
    protected static Object monitor;
    protected static Deque<File> dequeOfFiles = new ArrayDeque<File>();
    protected static List<String> result = new ArrayList<String>();
    protected static Deque<ThreadsForSearch> threadsForSearches = new ArrayDeque<ThreadsForSearch>();
    private static String name = "fnd";
    private String[] parts;
    private static final int DEFAULT_QUANTITY_THREADS = 50;
    private static int quantityTreads;
    protected static String fileName;
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
        this.currentDirectory = currentDirectory;
        monitor = new Object();
        if (checkParts(args)) {
            fileName = args;
            quantityTreads = DEFAULT_QUANTITY_THREADS;
        } else {
            fileName = parts[0];
            quantityTreads = Integer.parseInt(parts[1]);
        }
        for (int i = 0; i < quantityTreads; i++) {
            threadsForSearches.add(new ThreadsForSearch());
        }
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {}
        while (!(dequeOfFiles.size() == 0)){
            if (threadsForSearches.size() == 0) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {}
                }
            } else {
                threadsForSearches.poll().start();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        System.out.println();
        if (result.size() != 0){
            for (String s : result) {
                System.out.println(s);
            }
        }else System.out.println("File not found.");
        return currentDirectory;
    }

    @Override
    public void run() {
        if (currentDirectory.isDirectory()){
            if (currentDirectory.canRead()){
                for (File temp : currentDirectory.listFiles()){
                    if (temp.isDirectory()){
                        dequeOfFiles.add(temp);
                    }else if (temp.getName().equals(fileName)) {
                        result.add(temp.getPath() + " found");
                    }
                }
            }
        } else if (currentDirectory.isFile()){
            if (currentDirectory.getName().equals(fileName)) {
                result.add(currentDirectory.getPath() + " found");
            } else {
                System.out.println(currentDirectory.getPath() + " is not folder. Try another command");
            }
        } else {
            System.out.println(currentDirectory.getPath() + " is not folder. Try another command");
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
}

class ThreadsForSearch extends Thread{
    @Override
    public void run() {
        File file = FindWithTreads.dequeOfFiles.poll();
        System.out.println(Thread.currentThread());
        if (file.isDirectory()){
            if (file.canRead()){
                for (File temp : file.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        FindWithTreads.dequeOfFiles.add(temp);
                    } else if (temp.getName().equals(FindWithTreads.fileName))
                        FindWithTreads.result.add(temp.getPath() + " found");
                }
            }
        }
        System.out.println(Thread.currentThread() + " finished");
        FindWithTreads.threadsForSearches.addLast(new ThreadsForSearch());
        synchronized (FindWithTreads.monitor) {
            FindWithTreads.monitor.notify();
        }
    }
}