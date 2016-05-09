package PhoneBook.Controller;

import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController implements AppStaticValues {
    @Autowired
    private IUserDAO iUserDAO;

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute User user, HttpSession session) {
        iUserDAO.insertUser(user);
        session.setAttribute(USER, iUserDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword()));
        return new ModelAndView(PAGE_USER_MAIN, INFO, "User " + user.toString() + " added. Welcome");
    }

    @RequestMapping(value = "/authorization.do", method = RequestMethod.GET)
    public String authorization(@ModelAttribute User user, HttpSession session) {
        session.setAttribute(USER, iUserDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword()));
        return PAGE_USER_MAIN;
    }

    @RequestMapping(value = "/logOut.do", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute(USER, null);
        return PAGE_MAIN;
    }
}
