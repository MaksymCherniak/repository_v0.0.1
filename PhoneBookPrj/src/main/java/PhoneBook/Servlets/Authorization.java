package PhoneBook.Servlets;

import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authorization", urlPatterns = "/authorization.do")
public class Authorization extends HttpServlet {
    private IUserDAO iUserDAO;
    private WebApplicationContext webApplicationContext;
    private static final String INFO_VALUE = "info";
    private static final String INFO_PAGE = "infoPage.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        iUserDAO = (IUserDAO) webApplicationContext.getBean("IUserDAO");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = iUserDAO.findUserByLoginAndPassword(login, password);
        if (user != null) {
            request.getSession().setAttribute("user", user.getId());
            request.setAttribute("user", user);
            request.getRequestDispatcher("userMainPage.jsp").forward(request, response);
        }
        request.setAttribute(INFO_VALUE, "User not found.");
        request.getRequestDispatcher(INFO_PAGE).forward(request, response);
    }
}
