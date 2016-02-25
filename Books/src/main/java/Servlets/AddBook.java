package Servlets;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class AddBook extends HttpServlet {
    private static Logger log = Logger.getLogger(AddBook.class.getName());
    private final static String ATTRIBUTE_INFO = "info";
    private final static String PAGE_OK = "pageInfo.jsp";
    private IBookDAO iBookDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        iBookDAO = new XmlBookDAO();
        iBookDAO.setId();
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        Double price = Double.parseDouble(request.getParameter("price"));
        LocalDate publishDate = localDateInit(request.getParameter("publishDate"));
        String description = request.getParameter("description");
        Book book = new Book(author, title, genre, price, publishDate, description);
        iBookDAO.addBook(book);
        request.setAttribute(ATTRIBUTE_INFO, new String("Book added"));
        request.getRequestDispatcher(PAGE_OK).forward(request, response);
    }

    private LocalDate localDateInit(String publishDate) {
        String[] partsOfDate = publishDate.split("-");
        return LocalDate.of(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));
    }
}
