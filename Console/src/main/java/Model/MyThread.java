package Model;

import java.io.File;
import java.sql.Time;

/**
 * Created by Max on 21.11.2015.
 */
public class MyThread extends Thread {
    private String fileName;
    private File currentDirectory;
    public MyThread(String fileName, File currentDirectory) {
        this.fileName = fileName;
        this.currentDirectory = currentDirectory;
    }
    public File getCurrentDirectory() {
        return currentDirectory;
    }
    @Override
    public void run() {
            while (!Thread.interrupted()) {
                if (currentDirectory.isDirectory()) {
                    if (currentDirectory.canRead()) {
                        for (File temp : currentDirectory.listFiles()) {
                            if (temp.isDirectory()) {
                                new FindWithTreads().execute(fileName, currentDirectory);
                                interrupt();
                            } else if (temp.getName().equals(fileName)) {
                                System.out.println(temp.getAbsoluteFile().toString());
                            }
                        }
                    }
                }
            }
    }
}
