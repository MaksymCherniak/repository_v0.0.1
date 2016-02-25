package Servlets;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintBook extends HttpServlet {
    private static Logger log = Logger.getLogger(PrintBook.class.getName());
    private static final String PARAMETER_ID = "id";
    private static final String ATTRIBUTE_BOOK = "book";
    private static final String PAGE_OK = "printBook.jsp";
    private static final String PAGE_ERROR = "main.do";
    private IBookDAO iBookDAO = new XmlBookDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAMETER_ID);
        if (idStr != null && !idStr.equals("")){
            try{
                Book book = iBookDAO.findBookById(idStr);
                if (book != null){
                    request.setAttribute(ATTRIBUTE_BOOK, book);
                    request.getRequestDispatcher(PAGE_OK).forward(request, response);
                    return;
                }
            } catch (Exception e){
                log.log(Level.INFO, "Exception: ", e);
            }
        }
        request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
    }
}
