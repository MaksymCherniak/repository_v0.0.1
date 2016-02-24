package Model.Commands;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Model.Entity.BookAttribute;

import java.util.logging.Logger;

public class UpdateGenre implements ICommand {
    private static Logger log = Logger.getLogger(UpdateGenre.class.getName());
    private static final String name = "updateg";
    private String[] parts;
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 3) {
            iBookDAO = new XmlBookDAO();
            iBookDAO.updateBook(BookAttribute.GENRE, parts[1], parts[2]);
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- update genre");
    }
}
