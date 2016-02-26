import DAO.XmlBookDAO;
import Entity.Book;
import Servlets.BookAdd;
import Servlets.BookDelete;
import Servlets.BookUpdate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookServlet extends Mockito {
    private XmlBookDAO xmlBookDAO = new XmlBookDAO();
    private static final String NOT_ADD = "Servlet didn't add book";
    private static final String BOOK_EXISTS = "Book exists";
    private static final String ID = "id";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String GENRE = "genre";
    private static final String PRICE = "price";
    private static final String PUBLISH_DATE = "publishDate";
    private static final String DESCRIPTION = "description";
    private static final String AUTHOR_VALUE = "testAuthor";
    private static final String TITLE_VALUE = "testTitle";
    private static final String GENRE_VALUE = "testGenre";
    private static final String PRICE_VALUE = "2.00";
    private static final String DESCRIPTION_VALUE = "testDescription";
    private Book book;

    @Before
    public void initializeDependencies() {
        LocalDate localDate = LocalDate.of(2000, 01, 01);
        book = new Book(AUTHOR_VALUE, TITLE_VALUE, GENRE_VALUE, Double.parseDouble(PRICE_VALUE), localDate, DESCRIPTION_VALUE);
    }

    @After
    public void tearDown() {
        xmlBookDAO.getFileXml().delete();
    }

    @Test
    public void bookAdd() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter(AUTHOR)).thenReturn(book.getAuthor());
        when(request.getParameter(TITLE)).thenReturn(book.getTitle());
        when(request.getParameter(GENRE)).thenReturn(book.getGenre());
        when(request.getParameter(PRICE)).thenReturn(book.getStringPrice());
        when(request.getParameter(PUBLISH_DATE)).thenReturn(book.getStringPublishDate());
        when(request.getParameter(DESCRIPTION)).thenReturn(book.getDescription());

        assertNull(BOOK_EXISTS, xmlBookDAO.findBookById(book.getStringId()));
        new BookAdd().doGet(request, response);

        verify(request, atLeast(1)).getParameter(AUTHOR);
        verify(request, atLeast(1)).getParameter(TITLE);
        verify(request, atLeast(1)).getParameter(GENRE);
        verify(request, atLeast(1)).getParameter(PRICE);
        verify(request, atLeast(1)).getParameter(PUBLISH_DATE);
        verify(request, atLeast(1)).getParameter(DESCRIPTION);

        assertNotNull(NOT_ADD, xmlBookDAO.findBookById(book.getStringId()));
        assertTrue("Book didn't delete", xmlBookDAO.deleteBook(book.getStringId()));
    }

    @Test
    public void bookDelete() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        assertNull(BOOK_EXISTS, xmlBookDAO.findBookById(book.getStringId()));
        xmlBookDAO.addBook(book);
        assertNotNull(NOT_ADD, xmlBookDAO.findBookById(book.getStringId()));

        when(request.getParameter(ID)).thenReturn(book.getStringId());
        new BookDelete().doGet(request, response);

        verify(request, atLeast(1)).getParameter(ID);

        assertNull("Servlet didn't delete book", xmlBookDAO.findBookById(book.getStringId()));
    }

    @Test
    public void bookUpdate() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        assertNull(BOOK_EXISTS, xmlBookDAO.findBookById(book.getStringId()));
        xmlBookDAO.addBook(book);
        assertNotNull(NOT_ADD, xmlBookDAO.findBookById(book.getStringId()));

        when(request.getParameter("bookId")).thenReturn(book.getStringId());
        when(request.getParameter(AUTHOR)).thenReturn("newTestAuthor");
        when(request.getParameter(TITLE)).thenReturn("newTestTitle");
        when(request.getParameter(GENRE)).thenReturn("newTestGenre");
        when(request.getParameter(PRICE)).thenReturn("5.00");
        when(request.getParameter(PUBLISH_DATE)).thenReturn("2010-01-01");
        when(request.getParameter(DESCRIPTION)).thenReturn("newTestDescription");

        new BookUpdate().doGet(request, response);

        verify(request, atLeast(1)).getParameter("bookId");
        verify(request, atLeast(1)).getParameter(AUTHOR);
        verify(request, atLeast(1)).getParameter(TITLE);
        verify(request, atLeast(1)).getParameter(GENRE);
        verify(request, atLeast(1)).getParameter(PRICE);
        verify(request, atLeast(1)).getParameter(PUBLISH_DATE);
        verify(request, atLeast(1)).getParameter(DESCRIPTION);

        assertTrue("Book didn't delete", xmlBookDAO.deleteBook(book.getStringId()));
    }
}
