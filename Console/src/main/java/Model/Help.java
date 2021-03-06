package Model;


import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class Help implements ICommand {
    private static String name = "help";

    public String getName() {
        return name;
    }
    public void printHelp() {
        System.out.println("-" + name + " -- show all available commands");
    }

    public File execute(String args, File currentDirectory) {
        System.out.println("All available commands: ");
        System.out.println();
        printHelp();
        new ChangeCurrentLocation().printHelp();
        new Find().printHelp();
        new FindWithTreads().printHelp();
        new FindWithConcurrent().printHelp();
        new ShowDirectoryContent().printHelp();
        new ShowFile().printHelp();
        new DeleteFile().printHelp();
        new CreateDir().printHelp();
        new ShowFolderStructure().printHelp();
        new CopyFile().printHelp();
        new CompareContentOfFiles().printHelp();
        new Exit().printHelp();
        System.out.println();
        return currentDirectory;
    }
}
