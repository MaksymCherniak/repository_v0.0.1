package Service;

import DAO.Factory;
import DAO.IBookDAO;
import Entity.Book;
import Entity.BookAttribute;

import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(endpointInterface = "Service.BookService")
public class BookServiceImpl implements BookService {
    private static Logger log = Logger.getLogger(BookServiceImpl.class.getName());
    private IBookDAO iBookDAO = Factory.getXmlIBookDAO();
    private Book book;

    public List<Book> getAllBooks() {
        return iBookDAO.getAllBooks();
    }

    public String addBook(String id, String author, String title, String genre, String price, String publishDate, String description) {
        Double dPrice = priceCheck(price);
        LocalDate localDate = localDateChecker(publishDate);
        if (dPrice != null) {
            if (localDate != null) {
                book = new Book(id, author, title, genre, price, publishDate, description);
                if (iBookDAO.addBook(book)) {
                    return "Book added";
                } else {
                    return "Book didn't added. Maybe the book with same id already exists";
                }
            } else {
                return "Error. Wrong date format. Date must be like \"YYYY-MM-DD\"";
            }
        } else {
            return "Error. Wrong price format or price <= 0. Price must be like \"00.00\"";
        }
    }

    public String deleteBook(String id) {
        if (iBookDAO.deleteBook(id)) {
            return "Book deleted";
        }
        return "Book didn't delete";
    }

    public String updateBook(String bookAttribute, String id, String attribute) {
        /** Author */
        if (bookAttribute.equals("author")) {
            iBookDAO.updateBook(BookAttribute.AUTHOR, id, attribute);
            return "Attribute " + bookAttribute + " updated";
        }
        /** Title */
        if (bookAttribute.equals("title")) {
            iBookDAO.updateBook(BookAttribute.TITLE, id, attribute);
            return "Attribute " + bookAttribute + " updated";
        }
        /** Genre */
        if (bookAttribute.equals("genre")) {
            iBookDAO.updateBook(BookAttribute.GENRE, id, attribute);
            return "Attribute " + bookAttribute + " updated";
        }
        /** Price */
        if (bookAttribute.equals("price")) {
            if (priceCheck(attribute) != null && priceCheck(attribute) > 0) {
                iBookDAO.updateBook(BookAttribute.PRICE, id, attribute);
                return "Attribute " + bookAttribute + " updated";
            } else {
                return "Error. Wrong price format or price <= 0. Price must be like \"00.00\\";
            }
        }
        /** Publish date */
        if (bookAttribute.equals("publishDate")) {
            if (localDateChecker(attribute) != null) {
                iBookDAO.updateBook(BookAttribute.PUBLISH_DATE, id, attribute);
                return "Attribute " + bookAttribute + " updated";
            } else {
                return "Error. Wrong date format. Date must be like \"YYYY-MM-DD\"";
            }
        }
        /** Description */
        if (bookAttribute.equals("description")) {
            iBookDAO.updateBook(BookAttribute.DESCRIPTION, id, attribute);
            return "Attribute " + bookAttribute + " updated";
        }
        return "Wrong attribute";
    }

    public List<Book> changeBook(List<Book> requestXml) {
        for (Book book : requestXml) {
            System.out.println(book.toString());
        }
        return iBookDAO.changeBook(requestXml);
    }

    private LocalDate localDateChecker(String publishDate) {
        String[] partsOfDate = publishDate.split("-");
        try {
            return LocalDate.of(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }

    public Double priceCheck(String price) {
        try {
            return Double.parseDouble(price);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
