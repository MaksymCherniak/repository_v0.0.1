package Service;

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
    private IBookDAO iBookDAO;

    public List<Book> getAllBooks() {
        return iBookDAO.getAllBooks();
    }

    public String addBook(String id, String author, String title, String genre, String price, String publishDate
            , String description) {

        if (priceChecker(price) != null && priceChecker(price) > 0) {
            if (publishDateChecker(publishDate) != null) {
                if (iBookDAO.addBook(new Book(id, author, title, genre, price, publishDate, description))) {
                    return "Book added";
                } else {
                    return "Book didn't added. Maybe the book with same id already exists";
                }
            } else {
                return "Error. Wrong date format. Date must be like \"YYYY-MM-DD\"";
            }
        } else {
            return "Error. Wrong price format or price <= 0";
        }
    }

    public String deleteBook(String id) {
        if (iBookDAO.deleteBook(id)) {
            return "Book deleted";
        }
        return "Book didn't delete. Wrong id";
    }

    public String updateBook(String bookAttribute, String id, String attribute) {
        /** ------------------- Author ----------------------- */
        if (bookAttribute.equals(AUTHOR)) {
            iBookDAO.updateBook(BookAttribute.AUTHOR, id, attribute);
            return printUpdateResult(bookAttribute);
        }

        /** -------------------- Title ----------------------- */
        if (bookAttribute.equals(TITLE)) {
            iBookDAO.updateBook(BookAttribute.TITLE, id, attribute);
            return printUpdateResult(bookAttribute);
        }

        /** --------------------- Genre ---------------------- */
        if (bookAttribute.equals(GENRE)) {
            iBookDAO.updateBook(BookAttribute.GENRE, id, attribute);
            return printUpdateResult(bookAttribute);
        }

        /** --------------------- Price ---------------------- */
        if (bookAttribute.equals(PRICE)) {
            if (priceChecker(attribute) != null && priceChecker(attribute) > 0) {
                iBookDAO.updateBook(BookAttribute.PRICE, id, attribute);
                return printUpdateResult(bookAttribute);
            } else {
                return "Error. Wrong price format or price <= 0";
            }
        }

        /** ------------------ Publish date ------------------ */
        if (bookAttribute.equals(PUBLISH_DATE)) {
            if (publishDateChecker(attribute) != null) {
                iBookDAO.updateBook(BookAttribute.PUBLISH_DATE, id, attribute);
                return printUpdateResult(bookAttribute);
            } else {
                return "Error. Wrong date format. Date must be like \"YYYY-MM-DD\"";
            }
        }

        /** ------------------ Description ------------------- */
        if (bookAttribute.equals(DESCRIPTION)) {
            iBookDAO.updateBook(BookAttribute.DESCRIPTION, id, attribute);
            return printUpdateResult(bookAttribute);
        }
        return "Wrong attribute";
    }

    public List<Book> changeBook(List<Book> requestXml) {
        for (Book book : requestXml) {
            System.out.println(book.toString());
        }
        return iBookDAO.changeBook(requestXml);
    }

    private LocalDate publishDateChecker(String publishDate) {
        String[] partsOfDate = publishDate.split("-");
        try {
            return LocalDate.of(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }

    private String printUpdateResult(String bookAttribute) {
        return "Attribute " + bookAttribute + " updated";
    }

    private Double priceChecker(String price) {
        try {
            return Double.parseDouble(price);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }

    public void setiBookDAO(IBookDAO iBookDAO) {
        this.iBookDAO = iBookDAO;
    }
}
