package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Max on 22.11.2015.
 */
public class FindWithConcurrent extends Thread implements ICommand{
   // private static volatile List<String> result = new ArrayList<String>();
    private static ExecutorService service;
    private static String name = "findcon";
    private String[] parts;
    private String fileName;
    private File currentDirectory;
    private static final int DEFAULT_QUANTITY_THREADS = 50;
    public void printHelp() {
        System.out.println("-" + name + " \"name\" -- find file using concurrent package, with quantity of threads by default");
        System.out.println("-" + name + " \"name\" N -- find file using concurrent package, N - quantity of threads");
    }
    public FindWithConcurrent() {}
    public FindWithConcurrent(ExecutorService service, String fileName, File currentDirectory) {
        this.service = service;
        this.fileName = fileName;
        this.currentDirectory = currentDirectory;
    }
    public FindWithConcurrent(String fileName, File currentDirectory) {
        this.fileName = fileName;
        this.currentDirectory = currentDirectory;
    }

    public File execute(String args, File currentDirectory) {
        if (checkParts(args)){
            fileName = args;
            service = Executors.newFixedThreadPool(DEFAULT_QUANTITY_THREADS);
        }else{
            fileName = parts[0];
            service = Executors.newFixedThreadPool(Integer.parseInt(parts[1]));
        }
        this.currentDirectory = currentDirectory;

        service.execute(this);

        return currentDirectory;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    System.out.println(temp.getPath());
                    if (temp.isDirectory()) {
                        service.execute(new FindWithConcurrent(service, fileName, temp));
                    } else if (temp.getName().equals(fileName)) {
                        System.out.println(temp.getAbsoluteFile().toString() + " found");
                    }
                }
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
}
