package Model.Commands;

import java.util.logging.Logger;

public class Help implements ICommand {
    private static Logger log = Logger.getLogger(Help.class.getName());
    private final static String name = "help";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");

        if (parts.length == 1) {
            new Help().printHelp();
            new Exit().printHelp();
            new AddBook().printHelp();
            new DeleteBook().printHelp();
            new UpdateAuthor().printHelp();
            new UpdateDescription().printHelp();
            new UpdateGenre().printHelp();
            new UpdatePrice().printHelp();
            new UpdatePublishDate().printHelp();
            new UpdateTitle().printHelp();
            new PrintAllBooks().printHelp();
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all available commands");
    }
}
