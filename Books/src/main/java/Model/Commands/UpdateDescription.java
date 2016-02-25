package Model.Commands;

import DAO.IBookDAO;
import Model.Entity.BookAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class UpdateDescription implements ICommand {
    private static Logger log = Logger.getLogger(UpdateDescription.class.getName());
    private static final String name = "updated";
    private String[] parts;
    @Autowired
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 3) {
            iBookDAO.updateBook(BookAttribute.DESCRIPTION, parts[1], parts[2]);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- update description");
    }
}
