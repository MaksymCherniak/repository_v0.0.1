package BookingTool.Interceptors;

import BookingTool.Controller.IControllerStaticValues;
import BookingTool.DAO.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUserAuthorization extends HandlerInterceptorAdapter implements IControllerStaticValues {
    @Autowired
    private IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = request.getParameter(USER_EMAIL);
        String password = request.getParameter(USER_PASSWORD);
        if (iUserService.getUserByLoginAndPassword(login, password) == null) {
            request.setAttribute(INFO, "Wrong login or password.");
            request.getRequestDispatcher(JSP_PAGE_AUTHORIZATION).forward(request, response);
            return false;
        }
        return true;
    }
}
