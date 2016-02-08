package BookingTool.Model.ConsoleCommands;

public interface ICommand {
    void execute(String fullLine);

    void printHelp();
}