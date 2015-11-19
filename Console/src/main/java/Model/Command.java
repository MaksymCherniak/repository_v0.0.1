package Model;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 18.11.2015.
 */
public interface Command {
    String getName();
    void printHelp();
    void execute();
    File execute(String args, File currentDirectory) throws IOException;
    File execute(File currentDirectory);
}
