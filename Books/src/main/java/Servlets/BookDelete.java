package Servlets;

import DAO.Factory;
import DAO.IBookDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class BookDelete extends HttpServlet {
    private static Logger log = Logger.getLogger(BookDelete.class.getName());
    private final static String ATTRIBUTE_INFO = "info";
    private final static String PAGE_OK = "pageOk.jsp";
    private final static String PAGE_ERROR = "pageError.jsp";
    private IBookDAO iBookDAO = Factory.getXmlIBookDAO();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (iBookDAO.deleteBook(id)) {
            response.sendRedirect(PAGE_OK);
        } else {
            request.setAttribute(ATTRIBUTE_INFO, new String("Error. Book didn't delete. Please check book id."));
            request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
        }
    }
}
