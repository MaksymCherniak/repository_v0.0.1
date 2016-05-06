package PhoneBook.Controller;

import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController implements AppStaticValues {
    @Autowired
    private IUserDAO iUserDAO;

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute User user) {
        iUserDAO.insertUser(user);
        return new ModelAndView(PAGE_MAIN, INFO, "User " + user.toString() + " added");
    }

    @RequestMapping(value = "/authorization.do", method = RequestMethod.GET)
    public ModelAndView authorization(@ModelAttribute User user) {
        return new ModelAndView(PAGE_USER_MAIN, USER, iUserDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword()));
    }

    @RequestMapping(value = "/logOut.do", method = RequestMethod.GET)
    public ModelAndView logout() {
        return new ModelAndView(PAGE_MAIN);
    }
}
