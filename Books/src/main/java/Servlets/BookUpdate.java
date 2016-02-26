package Servlets;

import DAO.Factory;
import DAO.IBookDAO;
import Entity.BookAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookUpdate extends HttpServlet {
    private static Logger log = Logger.getLogger(BookUpdate.class.getName());
    private final static String ATTRIBUTE_INFO = "info";
    private final static String PAGE_OK = "pageOk.jsp";
    private final static String PAGE_ERROR = "pageError.jsp";
    private IBookDAO iBookDAO = Factory.getXmlIBookDAO();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("bookId");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String price = request.getParameter("price");
        String publishDate = request.getParameter("publishDate");
        String description = request.getParameter("description");
        /** Author */
        if (!author.equals("")) {
            iBookDAO.updateBook(BookAttribute.AUTHOR, id, author);
        }
        /** Title */
        if (!title.equals("")) {
            iBookDAO.updateBook(BookAttribute.TITLE, id, title);
        }
        /** Genre */
        if (!genre.equals("")) {
            iBookDAO.updateBook(BookAttribute.GENRE, id, genre);
        }
        /** Price */
        if (!price.equals("")) {
            if (priceCheck(price) != null && priceCheck(price) > 0) {
                iBookDAO.updateBook(BookAttribute.PRICE, id, price);
            } else {
                request.setAttribute(ATTRIBUTE_INFO, new String("Error. Wrong price format or price <= 0. Price must be like \"00.00\\"));
                request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
            }
        }
        /** Publish date */
        if (!publishDate.equals("")) {
            if (localDateChecker(publishDate) != null) {
                iBookDAO.updateBook(BookAttribute.PUBLISH_DATE, id, publishDate);
            } else {
                request.setAttribute(ATTRIBUTE_INFO, new String("Error. Wrong date format. Date must be like \"YYYY-MM-DD\""));
                request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
            }
        }
        /** Description */
        if (!description.equals("")) {
            iBookDAO.updateBook(BookAttribute.DESCRIPTION, id, description);
        }
        response.sendRedirect(PAGE_OK);
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
