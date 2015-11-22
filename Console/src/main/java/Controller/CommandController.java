package Controller;

import Model.*;

import java.io.File;

/**
 * Created by Max on 18.11.2015.
 */
public class CommandController {
    private File currentDirectory;
    public CommandController() {}
    public void setCurrentDirectory(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String getCurrentDirectory()
    {
        return currentDirectory.getPath();
    }

    public void searchCommand(String fullLine)
    {
        ParsedCommand parsedCommand = new ParsedCommand();
        if (parsedCommand.setCommand(fullLine)) {
             commandsWithoutArgs(parsedCommand);
        }
    }

    public void commandsWithoutArgs(ParsedCommand parsedCommand)
    {
        if (parsedCommand.parts == null) {
            if (parsedCommand.command.equalsIgnoreCase("help")) {
                new Help().execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("cd")) {
                currentDirectory = new ChangeCurrentLocation().execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("cd-help")) {
                new ChangeCurrentLocation().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("find-help")) {
                new Find().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("dir")) {
                new ShowDirectoryContent().execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("dir-help")) {
                new ShowDirectoryContent().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("type-help")) {
                new ShowFile().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("del-help")) {
                new DeleteFile().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("mkdir-help")) {
                new CreateDir().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("tree")) {
                new ShowFolderStructure().execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("tree-help")) {
                new ShowFolderStructure().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("copy-help")) {
                new CopyFile().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("fc-help")) {
                new CompareContentOfFiles().printHelp();
            } else if (parsedCommand.command.equalsIgnoreCase("exit")) {
                new Exit().execute(parsedCommand.args, currentDirectory);
            } else if (parsedCommand.command.equalsIgnoreCase("exit-help")) {
                new Exit().printHelp();
            } else {
                System.out.println("Wrong command. ");
            }
        } else commandsWithArgs(parsedCommand);
    }
    public void commandsWithArgs(ParsedCommand parsedCommand){
        if (parsedCommand.command.equalsIgnoreCase("find")) {
            new Find().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("cd")) {
            currentDirectory = new ChangeCurrentLocation().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("type")) {
            new ShowFile().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("del")) {
            new DeleteFile().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("mkdir")) {
            currentDirectory = new CreateDir().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("copy")) {
            new CopyFile().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("fc")) {
            new CompareContentOfFiles().execute(parsedCommand.args, currentDirectory);
        } else if (parsedCommand.command.equalsIgnoreCase("fnd")) {
            new FindWithTreads().execute(parsedCommand.args, currentDirectory);
        }else if (parsedCommand.command.equalsIgnoreCase("findc")){
            new FindWithConcurrent().execute(parsedCommand.args, currentDirectory);
        } else {
            System.out.println("Wrong command. ");
        }
    }
}
