package PhoneBook.Interceptors;

import PhoneBook.Controller.AppStaticValues;
import PhoneBook.DAO.Service.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckAuthorization extends HandlerInterceptorAdapter implements AppStaticValues {
    @Autowired
    private IUserDAO iUserDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (iUserDAO.findUserByLoginAndPassword(request.getParameter(LOGIN), request.getParameter(PASSWORD)) == null) {
            request.setAttribute(INFO, "Wrong login or password");
            request.getRequestDispatcher(JSP_PAGE_MAIN).forward(request, response);
            return false;
        }
        return true;
    }
}
