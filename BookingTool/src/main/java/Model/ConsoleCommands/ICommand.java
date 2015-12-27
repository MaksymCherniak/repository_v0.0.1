package Model.ConsoleCommands;

/**
 * Created by Max on 02.12.2015.
 */
public interface ICommand {
    void execute(int seatNumber, String lastName, String firstName);

    void printHelp();
}
