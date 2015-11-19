package Model;


import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class Help implements Command{
    private static String name = "help";

    public String getName() {
        return name;
    }
    public void printHelp() {
        System.out.println("-" + name + " -- show all available commands");
    }
    public void execute()
    {
        System.out.println("All available commands: ");
        System.out.println();
        printHelp();
        new ChangeCurrentLocation().printHelp();
        new Find().printHelp();
        new ShowDirectoryContent().printHelp();
        new ShowFile().printHelp();
        new DeleteFile().printHelp();
        new CreateDir().printHelp();
        new ShowFolderStructure().printHelp();
        new CopyFile().printHelp();
        new CompareContentOfFiles().printHelp();
        System.out.println();
    }
    public File execute(String args, File currentDirectory) {
        return currentDirectory;
    }

    public File execute(File currentDirectory) {
        return null;
    }
}
