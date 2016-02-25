package Servlets;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Entity.BookAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateBook extends HttpServlet {
    private static Logger log = Logger.getLogger(UpdateBook.class.getName());
    private final static String ATTRIBUTE_INFO = "info";
    private final static String PAGE_OK = "pageInfo.jsp";
    private IBookDAO iBookDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        iBookDAO = new XmlBookDAO();
        String id = request.getParameter("bookId");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String price = request.getParameter("price");
        String publishDate = request.getParameter("publishDate");
        String description = request.getParameter("description");
        if (!author.equals("")) {
            iBookDAO.updateBook(BookAttribute.AUTHOR, id, author);
        }
        if (!title.equals("")) {
            iBookDAO.updateBook(BookAttribute.TITLE, id, title);
        }
        if (!genre.equals("")) {
            iBookDAO.updateBook(BookAttribute.GENRE, id, genre);
        }
        if (!price.equals("") && priceCheck(price) != null) {
            iBookDAO.updateBook(BookAttribute.PRICE, id, price);
        } else {
            request.setAttribute(ATTRIBUTE_INFO, new String("Error. Wrong price format. Price must be like \"00.00\\"));
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
        }
        if (!publishDate.equals("") && localDateChecker(publishDate) != null) {
            iBookDAO.updateBook(BookAttribute.PUBLISH_DATE, id, publishDate);
        } else {
            request.setAttribute(ATTRIBUTE_INFO, new String("Error. Wrong date format. Date must be like \"YYYY-MM-DD\""));
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
        }
        if (!description.equals("")) {
            iBookDAO.updateBook(BookAttribute.DESCRIPTION, id, description);
        }
        request.setAttribute(ATTRIBUTE_INFO, new String("Book updated"));
        request.getRequestDispatcher(PAGE_OK).forward(request, response);
    }

    private LocalDate localDateChecker(String publishDate) {
        String[] partsOfDate = publishDate.split("-");
        try {
            return LocalDate.of(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }

    public Double priceCheck(String price) {
        try {
            return Double.parseDouble(price);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
