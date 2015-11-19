package Controller;

import Model.*;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class Controller {
    private String fullLine = "";
    private File currentDirectory;
    public Controller()
    {
        currentDirectory = new File("C:/");
    }
    public void setFullLine(String fullLine)
    {
        this.fullLine = fullLine;
    }
    public String getCurrentDirectory()
    {
        return currentDirectory.getPath();
    }

    public void searchCommand()
    {
        ParsedCommand parsedCommand = new ParsedCommand(fullLine);
        if (parsedCommand.setCommand()) {
            if (parsedCommand.command.equalsIgnoreCase("help")) {
                new Help().execute();
            } else if (parsedCommand.command.equalsIgnoreCase("cd")) {
                if (parsedCommand.parts == null) {
                    ChangeCurrentLocation changeCurrentLocation = new ChangeCurrentLocation();
                    currentDirectory = changeCurrentLocation.execute(currentDirectory);
                } else {
                    ChangeCurrentLocation changeCurrentLocation = new ChangeCurrentLocation();
                    currentDirectory = changeCurrentLocation.execute(parsedCommand.args, currentDirectory);
                }
            } else if (parsedCommand.command.equalsIgnoreCase("cd-help")) {
                new ChangeCurrentLocation().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("find")) {
                Find find = new Find();
                find.execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("find-help")) {
                new Find().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("dir")) {
                ShowDirectoryContent showDirectoryContent = new ShowDirectoryContent();
                showDirectoryContent.execute(currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("dir-help")) {
                new ShowDirectoryContent().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("type")) {
                ShowFile showFile = new ShowFile();
                showFile.execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("type-help")) {
                new ShowFile().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("del")) {
                DeleteFile deleteFile = new DeleteFile();
                deleteFile.execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("del-help")) {
                new DeleteFile().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("mkdir")) {
                CreateDir createDir = new CreateDir();
                currentDirectory = createDir.execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("mkdir-help")) {
                new CreateDir().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("tree")) {
                ShowFolderStructure showFolderStructure = new ShowFolderStructure();
                showFolderStructure.execute(currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("tree-help")) {
                new ShowFolderStructure().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("copy")) {
                CopyFile copyFile = new CopyFile();
                copyFile.execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("copy-help")) {
                new CopyFile().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("fc")) {
                CompareContentOfFiles compareContentOfFiles = new CompareContentOfFiles();
                compareContentOfFiles.execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("fc-help")) {
                new CompareContentOfFiles().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("exit")) {
                new Exit().execute();
            } else if (parsedCommand.command.equalsIgnoreCase("exit-help")) {
                new Exit().printHelp();
            } else System.out.println("Wrong command. ");
        }
    }
}
