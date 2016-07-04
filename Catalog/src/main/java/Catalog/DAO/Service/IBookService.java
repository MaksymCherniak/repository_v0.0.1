package Catalog.DAO.Service;

import Catalog.Entity.Book;
import Catalog.Entity.Enum.GenreBook;

import java.util.List;

public interface IBookService {
    void addBook(Book book);

    void deleteBook(long id);

    Book getBookById(long id);

    List<Book> getBooksByUserId(long user_id);

    List<Book> getBooksByGenre(GenreBook genre);
}
