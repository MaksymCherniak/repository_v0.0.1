package PhoneBook.Servlets;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.Entity.Contact;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Max on 23.04.2016.
 */
@WebServlet(name = "DeleteContact", urlPatterns = "/deleteContact.do")
public class DeleteContact extends HttpServlet {
    private IContactDAO iContactDAO;
    private WebApplicationContext webApplicationContext;
    private static final String INFO_VALUE = "info";
    private static final String INFO_PAGE = "infoPage.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iContactDAO = (IContactDAO) webApplicationContext.getBean("IContactDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long contact_id = Long.parseLong(request.getParameter("contact_id"));
        iContactDAO.removeContactById(contact_id);
        request.setAttribute(INFO_VALUE, "Contact deleted.");
        request.getRequestDispatcher(INFO_PAGE).forward(request, response);
    }
}
