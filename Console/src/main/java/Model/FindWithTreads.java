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
public class FindWithTreads implements ICommand{
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
    public File getCurrentDirectory() { return currentDirectory; }
    public String getName() { return name; }

    public void printHelp() {
        System.out.println("-" + name + " \"name\" -- find file using threads, with quantity of threads by default");
        System.out.println("-" + name + " \"name\" N -- find file using threads, N - quantity of threads");}

    public File execute(String args, File currentDirectory) {
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

        dequeOfFiles.add(currentDirectory); //Добавляем в очередь файлов текущую папку

        ThreadsForSearch firstThread = threadsForSearches.poll();
        firstThread.start();
        try {
            firstThread.join();
        } catch (InterruptedException e) {}

        while (threadsForSearches.size() == 0 || dequeOfFiles.size() == 0){ //Запустить все очереди по доступным подпапкам
            threadsForSearches.poll().start();
        }

        threadsManager();
        printResult();

        return currentDirectory;
    }

    /**Проверка входящей строки на наявность аргумента(количество нитей) */
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

    /**Менеджер потоков */
    public void threadsManager(){
        do{
            if (threadsForSearches.size() == 0 || dequeOfFiles.size() == 0) {
                synchronized (monitor) {
                    try {
                        System.out.println(" Waiting ");
                        monitor.wait();
                    } catch (InterruptedException e) {}
                }
            } else {
                synchronized (threadsForSearches) {
                    threadsForSearches.poll().start();
                }
            }
        } while ((threadsForSearches.size() != quantityTreads) && (dequeOfFiles.size() != 0));
    }

    /**Вывод результата */
    public void printResult(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        int i = result.size();
        System.out.println();
        System.out.println("Найдено " + i + " файлов.");
        for (String s : result){
            System.out.println(s);
        }
        System.out.println();
    }
}

/**Класс нитей */
class ThreadsForSearch extends Thread{
    @Override
    public void run() {
        File file;
        synchronized (FindWithTreads.dequeOfFiles) {
            file = FindWithTreads.dequeOfFiles.poll();
        }
        System.out.println(Thread.currentThread());
        if (file.isDirectory()) {
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        synchronized (FindWithTreads.dequeOfFiles) {
                            FindWithTreads.dequeOfFiles.add(temp);
                        }
                    } else if (temp.getName().equals(FindWithTreads.fileName))
                        FindWithTreads.result.add(temp.getPath() + " found");
                }
            }
        } else if (file.isFile()){
            if (file.getName().equals(FindWithTreads.fileName)) {
                FindWithTreads.result.add(file.getPath() + " found");
            } else {
                System.out.println(file.getPath() + " is not folder. Try another command");
            }
        } else {
            System.out.println(file.getPath() + " is not folder. Try another command");
        }
        System.out.println(Thread.currentThread() + " finished");
        synchronized (FindWithTreads.threadsForSearches) {
            FindWithTreads.threadsForSearches.addLast(new ThreadsForSearch());
        }
        synchronized (FindWithTreads.monitor) {
            FindWithTreads.monitor.notify();
        }
    }
}