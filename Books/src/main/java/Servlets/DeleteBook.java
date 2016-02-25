package Servlets;

import DAO.IBookDAO;
import DAO.XmlBookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteBook extends HttpServlet {
    private static Logger log = Logger.getLogger(DeleteBook.class.getName());
    private final static String ATTRIBUTE_INFO = "info";
    private final static String PAGE_OK = "pageInfo.jsp";
    private IBookDAO iBookDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        iBookDAO = new XmlBookDAO();
        String id = request.getParameter("id");
        if (iBookDAO.deleteBook(id)) {
            request.setAttribute(ATTRIBUTE_INFO, new String("Book deleted"));
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
        } else {
            request.setAttribute(ATTRIBUTE_INFO, new String("Error"));
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
        }
    }
}
