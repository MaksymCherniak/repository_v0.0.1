package Model.Commands;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Model.Entity.BookAttribute;

import java.util.logging.Logger;

public class UpdateAuthor implements ICommand {
    private static Logger log = Logger.getLogger(UpdateAuthor.class.getName());
    private static final String name = "updatea";
    private String[] parts;
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 3) {
            iBookDAO = new XmlBookDAO();
            iBookDAO.updateBook(BookAttribute.AUTHOR, parts[1], parts[2]);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- update author");
    }
}
