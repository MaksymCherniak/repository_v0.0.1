package PhoneBook.Servlets;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.Contact;
import PhoneBook.Entity.User;
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

@WebServlet(name = "AddContact", urlPatterns = "/addContact.do")
public class AddContact extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long user_id = (Long) request.getSession().getAttribute("user");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String patronym = request.getParameter("patronym");
        String mobilePhone = request.getParameter("mobilePhone");
        String homePhone = request.getParameter("homePhone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        if (!Validator.surname(surname)) {
            request.setAttribute(INFO_VALUE, "Wrong surname format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!Validator.name(name)) {
            request.setAttribute(INFO_VALUE, "Wrong name format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!Validator.patronym(patronym)) {
            request.setAttribute(INFO_VALUE, "Wrong patronym format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        User user = iUserDAO.findUserById(user_id);
        Contact contact = new Contact(surname, name, patronym, email, address, mobilePhone, homePhone, user);
        iContactDAO.insertContact(contact);
        request.setAttribute(INFO_VALUE, contact.toString() + "\nadded");
        request.getRequestDispatcher(INFO_PAGE).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long user_id = Long.parseLong(request.getParameter("user_id"));
        User user = iUserDAO.findUserById(user_id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("addContact.jsp").forward(request, response);
    }
}
