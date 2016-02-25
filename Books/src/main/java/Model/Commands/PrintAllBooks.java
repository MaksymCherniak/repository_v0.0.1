package Model.Commands;

import DAO.IBookDAO;
import Model.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllBooks implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllBooks.class.getName());
    private static final String name = "printall";
    private String[] parts;
    @Autowired
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<Book> listOfBooks = iBookDAO.getAllBooks();
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
