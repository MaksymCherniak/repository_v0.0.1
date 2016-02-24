package DAO;

import Model.Entity.Book;
import Model.Entity.BookAttribute;
import org.w3c.dom.Element;

import java.util.List;

public interface IBookDAO {
    void addBook(Book book);

    void deleteBook(String id);

    Element findBook(String id);

    void updateBook(BookAttribute bookAttribute, String id, String attribute);

    List<Book> getAllBooks();

    void setId();
}
