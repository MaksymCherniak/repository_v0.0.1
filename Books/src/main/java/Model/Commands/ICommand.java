package Model.Commands;

public interface ICommand {
    void execute(String fullLine);

    void printHelp();
}
