package PhoneBook.Servlets;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.DAO.Service.IUserDAO;
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
import java.util.List;

@WebServlet(name = "PrintContact", urlPatterns = "/printContact.do")
public class PrintContact extends HttpServlet {
    private IUserDAO iUserDAO;
    private IContactDAO iContactDAO;
    private WebApplicationContext webApplicationContext;
    private static final String INFO_VALUE = "info";
    private static final String INFO_PAGE = "infoPage.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iUserDAO = (IUserDAO) webApplicationContext.getBean("IUserDAO");
        iContactDAO = (IContactDAO) webApplicationContext.getBean("IContactDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long contact_id = Long.parseLong(request.getParameter("id"));
        Long user_id = (Long) request.getSession().getAttribute("user");
        List<Contact> listOfContacts = iContactDAO.getAllContactsByUserId(user_id);
        Contact contact = iContactDAO.getContactById(contact_id);
        request.setAttribute("listOfContacts", listOfContacts);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("printAllContacts.jsp").forward(request, response);
    }
}
