package DAO;

import Entity.Book;
import Entity.BookAttribute;
import org.w3c.dom.Element;

import java.util.List;

public interface IBookDAO {
    void addBook(Book book);

    boolean deleteBook(String id);

    Element findBookElement(String id);

    Book findBookById(String id);

    void updateBook(BookAttribute bookAttribute, String id, String attribute);

    List<Book> getAllBooks();

    void setId();
}