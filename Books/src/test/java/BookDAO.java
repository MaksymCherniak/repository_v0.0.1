import DAO.XmlBookDAO;
import Entity.Book;
import Entity.BookAttribute;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookDAO {
    private File xmlTestFile = new File("test.xml");
    private XmlBookDAO xmlBookDAO = new XmlBookDAO();
    private Book book;
    private static final String AUTHOR = "TestAuthor";
    private static final String TITLE = "TestTitle";
    private static final String GENRE = "TestGenre";
    private static final Double PRICE = 10.00;
    private static final LocalDate PUBLISH_DATE = LocalDate.of(2000, 02, 02);
    private static final String DESCRIPTION = "TestDescription";

    @Before
    public void initializeDependencies() {
        xmlBookDAO.setFileXml(xmlTestFile);
        book = new Book(AUTHOR, TITLE, GENRE, PRICE, PUBLISH_DATE, DESCRIPTION);
    }

    @After
    public void tearDown() {
        xmlTestFile.delete();
    }

    @Test
    public void whenAddTheBookExists() {
        assertNull("Book exists before added", xmlBookDAO.findBookById(book.getStringId()));

        assertTrue("Book didn't added", xmlBookDAO.addBook(book));

        assertNotNull("Book does not exist after added", xmlBookDAO.findBookById(book.getStringId()));
    }

    @Test
    public void doubleRemoveBookIsImpossible() {
        xmlBookDAO.addBook(book);
        book = xmlBookDAO.findBookById(book.getStringId());

        assertTrue("Book didn't remove", xmlBookDAO.deleteBook(book.getIndex()));
        assertFalse("Book double removed", xmlBookDAO.deleteBook(book.getIndex()));
    }

    @Test
    public void canNotRemoveIfTheBookIsNotInTheXml() {
        assertFalse("The book is not in the xml-file removed", xmlBookDAO.deleteBook(book.getIndex()));
    }

    @Test
    public void updateBookAttributeIsSuccessful() {
        book = new Book(AUTHOR, TITLE, GENRE, PRICE, PUBLISH_DATE, DESCRIPTION);
        xmlBookDAO.addBook(book);

        String id = book.getStringId();
        String newAuthor = "NewAuthor";
        String newTitle = "NewTitle";
        String newGenre = "NewGenre";
        String newPrice = "1.01";
        String newPublishDate = "2010-10-10";
        String newDescription = "NewDescription";

        xmlBookDAO.updateBook(BookAttribute.AUTHOR, id, newAuthor);
        xmlBookDAO.updateBook(BookAttribute.TITLE, id, newTitle);
        xmlBookDAO.updateBook(BookAttribute.GENRE, id, newGenre);
        xmlBookDAO.updateBook(BookAttribute.PRICE, id, newPrice);
        xmlBookDAO.updateBook(BookAttribute.PUBLISH_DATE, id, newPublishDate);
        xmlBookDAO.updateBook(BookAttribute.DESCRIPTION, id, newDescription);

        book = xmlBookDAO.findBookById(book.getStringId());
        assertTrue("Author didn't change", book.getAuthor().equals(newAuthor));
        assertTrue("Title didn't change", book.getTitle().equals(newTitle));
        assertTrue("Genre didn't change", book.getGenre().equals(newGenre));
        assertTrue("Price didn't change", book.getPrice() == Double.parseDouble(newPrice));
        LocalDate changedPublishDate = LocalDate.of(2010, 10, 10);
        assertTrue("Publish date didn't change", book.getPublishDate().equals(changedPublishDate));
        assertTrue("Description didn't change", book.getDescription().equals(newDescription));
    }
}
