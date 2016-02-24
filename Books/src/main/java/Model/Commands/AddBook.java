package Model.Commands;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Model.Entity.Book;

import java.time.LocalDate;
import java.util.logging.Logger;

public class AddBook implements ICommand {
    private static Logger log = Logger.getLogger(AddBook.class.getName());
    private static final String name = "add";
    private String[] parts;
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 7) {
            iBookDAO = new XmlBookDAO();
            iBookDAO.setId();
            LocalDate publishDate = localDateInit(parts[5]);
            Double price = Double.parseDouble(parts[4]);
            Book book = new Book(parts[1], parts[2], parts[3], price, publishDate, parts[6]);
            iBookDAO.addBook(book);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- add book");
        System.out.println("    " + name + " \"author\" \"title\" \"genre\" \"price\" \"publish_date\"(YYYY-MM-DD) \"description\"");
    }

    private LocalDate localDateInit(String publishDate) {
        String[] partsOfDate = publishDate.split("-");
        return LocalDate.of(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));
    }
}
