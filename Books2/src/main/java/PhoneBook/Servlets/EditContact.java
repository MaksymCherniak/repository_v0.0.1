package PhoneBook.Servlets;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.Entity.Contact;
import PhoneBook.Entity.Validator;
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
@WebServlet(name = "EditContact", urlPatterns = "/editContact.do")
public class EditContact extends HttpServlet {
    private IContactDAO iContactDAO;
    private WebApplicationContext webApplicationContext;
    private static final String INFO_VALUE = "info";
    private static final String INFO_PAGE = "editContact.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iContactDAO = (IContactDAO) webApplicationContext.getBean("IContactDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Contact contact = iContactDAO.getContactById(Long.parseLong(request.getParameter("contact_id")));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronym = request.getParameter("patronym");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String home = request.getParameter("home");
        if (!name.equals("") && !Validator.name(name)) {
            request.setAttribute("contact", contact);
            request.setAttribute(INFO_VALUE, "Wrong name format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!surname.equals("") && !Validator.surname(surname)) {
            request.setAttribute("contact", contact);
            request.setAttribute(INFO_VALUE, "Wrong surname format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!patronym.equals("") && !Validator.patronym(patronym)) {
            request.setAttribute("contact", contact);
            request.setAttribute(INFO_VALUE, "Wrong patronym format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!email.equals("") && !Validator.email(email)) {
            request.setAttribute("contact", contact);
            request.setAttribute(INFO_VALUE, "Wrong e-mail format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!name.equals("")) {
            contact.setName(name);
        }
        if (!surname.equals("")) {
            contact.setSurname(surname);
        }
        if (!patronym.equals("")) {
            contact.setPatronym(patronym);
        }
        if (!email.equals("")) {
            contact.setEmail(email);
        }
        if (!address.equals("")) {
            contact.setAddress(address);
        }
        if (!mobile.equals("")) {
            contact.setMobilePhoneNumber(mobile);
        }
        if (!home.equals("")) {
            contact.setHomePhoneNumber(home);
        }
        iContactDAO.insertContact(contact);
        request.setAttribute(INFO_VALUE, "Contact edited.");
        request.getRequestDispatcher("infoPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long contact_id = Long.parseLong(request.getParameter("contact_id"));
        Contact contact = iContactDAO.getContactById(contact_id);
        request.setAttribute("contact", contact);
        request.setAttribute("contact_id", contact_id);
        request.getRequestDispatcher("editContact.jsp").forward(request, response);
    }
}
