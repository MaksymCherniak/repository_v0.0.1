package Servlets;

import DAO.Factory;
import DAO.IBookDAO;
import Entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookAdd extends HttpServlet {
    private static Logger log = Logger.getLogger(BookAdd.class.getName());
    private final static String ATTRIBUTE_INFO = "info";
    private final static String PAGE_OK = "pageOk.jsp";
    private final static String PAGE_ERROR = "pageError.jsp";
    private IBookDAO iBookDAO = Factory.getXmlIBookDAO();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        Double price = priceCheck(request.getParameter("price"));
        LocalDate publishDate = localDateChecker(request.getParameter("publishDate"));
        String description = request.getParameter("description");
        if (price != null && price > 0) {
            if (publishDate != null) {
                Book book = new Book(author, title, genre, price, publishDate, description);
                iBookDAO.addBook(book);
                response.sendRedirect(PAGE_OK);
            } else {
                request.setAttribute(ATTRIBUTE_INFO, new String("Error. Wrong date format. Date must be like \"YYYY-MM-DD\""));
                request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
            }
        } else {
            request.setAttribute(ATTRIBUTE_INFO, new String("Error. Wrong price format or price <= 0. Price must be like \"00.00\""));
            request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
        }
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
