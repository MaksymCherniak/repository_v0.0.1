package Model.Commands;

import DAO.IBookDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class DeleteBook implements ICommand {
    private static Logger log = Logger.getLogger(DeleteBook.class.getName());
    private static final String name = "delete";
    private String[] parts;
    @Autowired
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 2) {
            iBookDAO.deleteBook(parts[1]);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- delete book");
    }
}
