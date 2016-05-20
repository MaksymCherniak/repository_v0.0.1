package BookingTool.Interceptors;

import BookingTool.Controller.IControllerStaticValues;
import BookingTool.DAO.Service.IUserService;
import BookingTool.Entity.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUserRegistration extends HandlerInterceptorAdapter implements IControllerStaticValues {
    @Autowired
    private IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = request.getParameter(USER_EMAIL);
        String password = request.getParameter(USER_PASSWORD);
        String name = request.getParameter(USER_NAME);
        String surname = request.getParameter(USER_SURNAME);
        if (iUserService.getUserByLoginAndPassword(login, password) != null) {
            request.setAttribute(INFO, "User with the same login already exist");
            request.getRequestDispatcher(JSP_PAGE_REGISTRATION).forward(request, response);
            return false;
        }
        if (!Validator.email(login)) {
            return sendResponse(USER_EMAIL, request, response);
        }
        if (!Validator.password(password)) {
            return sendResponse(USER_PASSWORD, request, response);
        }
        if (!Validator.name(name)) {
            return sendResponse(USER_NAME, request, response);
        }
        if (!Validator.surname(surname)) {
            return sendResponse(USER_SURNAME, request, response);
        }
        return true;
    }

    private boolean sendResponse(String attribute, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(INFO, "Wrong " + attribute + " format.");
        request.getRequestDispatcher(JSP_PAGE_REGISTRATION).forward(request, response);
        return false;
    }
}
