package Model;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class DeleteFile implements Command {
    private String name = "del";
    private File currentDirectory;
    private String fileName;
    private boolean delete;
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- delete file or dir");
    }
    public void execute() {

    }

    public File execute(String args, File currentDirectory) {
        this.currentDirectory = currentDirectory;
        fileName = args;
        for (String s : currentDirectory.list())
        {
            if (s.equals(fileName))
            {
                delete = true;
                File delDir = new File(currentDirectory, fileName);
                delDir.delete();
                System.out.println("Delete \"" + delDir.getPath() + "\" ");
                System.out.println();
            }
        }
        if (delete)
        {}
        else System.out.println("File \"" + fileName + "\" not found!");
        return currentDirectory;
    }

    public File execute(File currentDirectory) {
        return null;
    }
}
