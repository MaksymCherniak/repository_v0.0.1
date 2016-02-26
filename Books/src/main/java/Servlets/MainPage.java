package Servlets;

import DAO.Factory;
import DAO.XmlBookDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class MainPage extends javax.servlet.http.HttpServlet {
    private static Logger log = Logger.getLogger(MainPage.class.getName());
    private final static String PAGE_PRINT_ALL = "printAllBooks.jsp";
    private final static String PAGE_ADD = "addBook.jsp";
    private final static String PAGE_DELETE = "deleteBook.jsp";
    private final static String PAGE_UPDATE = "updateBook.jsp";
    private final static String PAGE_ERROR = "/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Factory.getXmlIBookDAO().setId();
        String idStr = request.getServletPath();
        if (idStr.equals("/addBook.do")) {
            request.getRequestDispatcher(PAGE_ADD).forward(request, response);
        } else if (idStr.equals("/deleteBook.do")) {
            request.getRequestDispatcher(PAGE_DELETE).forward(request, response);
        } else if (idStr.equals("/updateBook.do")) {
            request.getRequestDispatcher(PAGE_UPDATE).forward(request, response);
        } else if (idStr.equals("/printAllBooks.do")) {
            request.getRequestDispatcher(PAGE_PRINT_ALL).forward(request, response);
        } else {
            request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
        }
    }
}
