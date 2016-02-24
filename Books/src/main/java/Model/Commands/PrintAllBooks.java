package Model.Commands;

import DAO.XmlBookDAO;
import Model.Entity.Book;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllBooks implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllBooks.class.getName());
    private static final String name = "printall";
    private String[] parts;
    private XmlBookDAO xmlBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            xmlBookDAO = new XmlBookDAO();
            List<Book> listOfBooks = xmlBookDAO.getAllBooks();
            for (Book book : listOfBooks) {
                System.out.println(book.toString());
                System.out.println("-------------------------------------------------------------------");
            }
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all books");
    }
}
