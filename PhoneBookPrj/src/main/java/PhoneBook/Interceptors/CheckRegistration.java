package PhoneBook.Interceptors;

import PhoneBook.Controller.AppStaticValues;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckRegistration extends HandlerInterceptorAdapter implements AppStaticValues {
    @Autowired
    private IUserDAO iUserDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String fullName = request.getParameter(FULL_NAME);
        if (iUserDAO.findUserByLoginAndPassword(login, password) != null) {
            request.setAttribute(INFO, "User with the same login already exist");
            request.getRequestDispatcher(JSP_REGISTER_PAGE).forward(request, response);
            return false;
        }
        if (!Validator.login(login) && !Validator.password(password) && !Validator.fullName(fullName)) {
            request.setAttribute(INFO, "Wrong login or password or full name format");
            request.getRequestDispatcher(JSP_REGISTER_PAGE).forward(request, response);
            return false;
        }
        return true;
    }
}
