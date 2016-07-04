package Catalog.DAO.Impl.MySQL;

import Catalog.DAO.Repository.BookRepository;
import Catalog.DAO.Service.IBookService;
import Catalog.Entity.Book;
import Catalog.Entity.Enum.GenreBook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MySQLBookImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.saveAndFlush(book);
    }

    public void deleteBook(long id) {
        bookRepository.delete(id);
    }

    public Book getBookById(long id) {
        return bookRepository.findOne(id);
    }

    public List<Book> getBooksByUserId(long user_id) {
        return bookRepository.getBooksByUserId(user_id);
    }

    public List<Book> getBooksByGenre(GenreBook genre) {
        return bookRepository.getBooksByGenre(genre);
    }
}
