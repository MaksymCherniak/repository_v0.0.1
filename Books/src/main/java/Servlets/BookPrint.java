package Servlets;

import DAO.Factory;
import DAO.IBookDAO;
import Entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookPrint extends HttpServlet {
    private static Logger log = Logger.getLogger(BookPrint.class.getName());
    private static final String PARAMETER_ID = "id";
    private static final String ATTRIBUTE_BOOK = "book";
    private static final String PAGE_OK = "printBook.jsp";
    private static final String PAGE_ERROR = "main.do";
    private IBookDAO iBookDAO = Factory.getXmlIBookDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAMETER_ID);
        if (idStr != null && !idStr.equals("")) {
            try {
                Book book = iBookDAO.findBookById(idStr);
                if (book != null) {
                    request.setAttribute(ATTRIBUTE_BOOK, book);
                    request.getRequestDispatcher(PAGE_OK).forward(request, response);
                    return;
                }
            } catch (Exception e) {
                log.log(Level.INFO, "Exception: ", e);
            }
        }
        request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
    }
}
