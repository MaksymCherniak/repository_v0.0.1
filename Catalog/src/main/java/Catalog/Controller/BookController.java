package Catalog.Controller;

import Catalog.DAO.Service.IBookService;
import Catalog.Entity.Book;
import Catalog.Entity.Enum.GenreBook;
import Catalog.Entity.Lists.ListGenreBook;
import Catalog.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController implements AppStaticValues {
    @Autowired
    private IBookService iBookService;

    @RequestMapping(value = "addBook", method = RequestMethod.GET)
    public ModelAndView addBook() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_ADD_BOOK);
        modelAndView.addObject(LIST_OF, ListGenreBook.getBookGenres());
        modelAndView.addObject(BOOK, new Book());

        return modelAndView;
    }

    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    public ModelAndView addBook(@ModelAttribute Book book, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        book.setUser(user);

        iBookService.addBook(book);
        List<Book> listOfBooks = iBookService.getBooksByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_BOOKS);
        modelAndView.addObject(INFO, book.toString() + "added");
        modelAndView.addObject(LIST_OF, listOfBooks);

        return modelAndView;
    }

    @RequestMapping(value = "deleteBook", method = RequestMethod.POST)
    public ModelAndView deleteBook(@RequestParam(value = BOOK_ID) String id) {
        Book book = iBookService.getBookById(Long.parseLong(id));
        iBookService.deleteBook(Long.parseLong(id));
        return new ModelAndView("infoPage", INFO, book.toString() + " removed.");
    }

    @RequestMapping(value = "getBooks", method = RequestMethod.GET)
    public ModelAndView getBooks(HttpSession session) {
        User user = (User) session.getAttribute(USER);
        List<Book> listOfBooks = iBookService.getBooksByUserId(user.getId());
        return new ModelAndView(PAGE_GET_BOOKS, LIST_OF, listOfBooks);
    }

    @RequestMapping(value = "printBook", method = RequestMethod.GET)
    public ModelAndView printBook(@RequestParam(value = BOOK_ID) String book_id) {
        return new ModelAndView(PAGE_PRINT_BOOK, BOOK, iBookService.getBookById(Long.parseLong(book_id)));
    }

    @RequestMapping(value = "getBooksByGenre", method = RequestMethod.GET)
    public ModelAndView getBooksByGenre() {
        return new ModelAndView(PAGE_GET_BOOKS_BY_GENRE, LIST_OF, ListGenreBook.getBookGenres());
    }

    @RequestMapping(value = "getBooksByGenre", method = RequestMethod.POST)
    public ModelAndView getBooksByGenre(@RequestParam(value = BOOK_GENRE) String genre) {
        return new ModelAndView(PAGE_GET_BOOKS, LIST_OF, iBookService.getBooksByGenre(GenreBook.valueOf(genre)));
    }
}
