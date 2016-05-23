package PhoneBook.Interceptors;

import PhoneBook.Controller.AppStaticValues;
import PhoneBook.Entity.Validator;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckAddContact extends HandlerInterceptorAdapter implements AppStaticValues {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!Validator.surname(request.getParameter(SURNAME))) {
            sendResponse(SURNAME, request, response);
            return false;
        }
        if (!Validator.name(request.getParameter(NAME))) {
            sendResponse(NAME, request, response);
            return false;
        }
        if (!Validator.patronym(request.getParameter(PATRONYM))) {
            sendResponse(PATRONYM, request, response);
            return false;
        }
        String email = request.getParameter(EMAIL);
        if (!email.equals("") && !Validator.email(email)) {
            sendResponse(EMAIL, request, response);
            return false;
        }
        if (!Validator.mobile(request.getParameter(MOBILE))) {
            sendResponse(MOBILE, request, response);
            return false;
        }
        return true;
    }

    private void sendResponse(String attribute, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(INFO, "Wrong " + attribute + " format.");
        request.getRequestDispatcher(JSP_PAGE_ADD_CONTACT).forward(request, response);
    }
}
