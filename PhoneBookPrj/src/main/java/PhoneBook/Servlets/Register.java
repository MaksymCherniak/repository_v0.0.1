package PhoneBook.Servlets;

import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import PhoneBook.Entity.Validator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Register extends HttpServlet {
    private IUserDAO iUserDAO;
    private WebApplicationContext webApplicationContext;
    private static final String INFO_VALUE = "info";
    private static final String INFO_PAGE = "infoPage.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iUserDAO = (IUserDAO) webApplicationContext.getBean("IUserDAO");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        if (!Validator.login(login)) {
            request.setAttribute(INFO_VALUE, "Wrong login format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!Validator.password(password)) {
            request.setAttribute(INFO_VALUE, "Wrong password format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        if (!Validator.fullName(fullName)) {
            request.setAttribute(INFO_VALUE, "Wrong full name format.");
            request.getRequestDispatcher(INFO_PAGE).forward(request, response);
        }
        User user = new User(fullName, login, password);
        iUserDAO.insertUser(user);
        request.setAttribute(INFO_VALUE, "User " + user.toString() + " added");
        request.getRequestDispatcher(INFO_PAGE).forward(request, response);
    }
}
