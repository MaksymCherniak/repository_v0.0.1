package Model;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 18.11.2015.
 */
public interface ICommand {
    String getName();
    void printHelp();
    File execute(String args, File currentDirectory) throws IOException;
}
