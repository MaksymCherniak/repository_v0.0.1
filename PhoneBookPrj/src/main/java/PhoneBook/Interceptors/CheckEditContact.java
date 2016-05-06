package PhoneBook.Interceptors;

import PhoneBook.Controller.AppStaticValues;
import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.Entity.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckEditContact extends HandlerInterceptorAdapter implements AppStaticValues {
    @Autowired
    private IContactDAO iContactDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String patronym = request.getParameter(PATRONYM);
        String mobile = request.getParameter(MOBILE);
        String email = request.getParameter(EMAIL);

        if (!name.equals("") && !Validator.name(name)) {
            sendResponse(NAME, request, response);
            return false;
        }
        if (!surname.equals("") && !Validator.surname(surname)) {
            sendResponse(SURNAME, request, response);
            return false;
        }
        if (!patronym.equals("") && !Validator.patronym(patronym)) {
            sendResponse(PATRONYM, request, response);
            return false;
        }
        if (!email.equals("") && !Validator.email(email)) {
            sendResponse(EMAIL, request, response);
            return false;
        }
        if (!mobile.equals("") && !Validator.mobile(mobile)) {
            sendResponse(MOBILE, request, response);
            return false;
        }
        return true;
    }

    private void sendResponse(String attribute, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(CONTACT, iContactDAO.getContactById(Long.parseLong(request.getParameter(CONTACT_ID))));
        request.setAttribute(INFO, "Wrong " + attribute + " format.");
        request.getRequestDispatcher(JSP_PAGE_EDIT_CONTACT).forward(request, response);
    }
}
