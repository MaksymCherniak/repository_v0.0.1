package DAO;

import Entity.Book;
import Entity.BookAttribute;
import org.w3c.dom.Element;

import java.util.List;

public interface IBookDAO {
    String ID = "id";
    String AUTHOR = "author";
    String TITLE = "title";
    String GENRE = "genre";
    String PRICE = "price";
    String PUBLISH_DATE = "publish_date";
    String DESCRIPTION = "description";

    List<Book> changeBook(List<Book> requestXml);

    boolean addBook(Book book);

    boolean deleteBook(String id);

    Element findBookElement(String id);

    Book findBookById(String id);

    void updateBook(BookAttribute bookAttribute, String id, String attribute);

    List<Book> getAllBooks();

}
