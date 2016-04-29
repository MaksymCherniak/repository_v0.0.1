package PhoneBook.Controller;

import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import PhoneBook.Entity.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private IUserDAO iUserDAO;
    private static final String REGISTER_PAGE = "registerPage";
    private static final String INFO = "info";
    private static final String USER = "user";
    private static final String MAIN = "main";
    private static final String AUTHORIZATION_PAGE = "authorizationPage";
    private static final String USER_PAGE = "userMainPage";

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "login") String login,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "fullName") String fullName) {
        ModelAndView modelAndView = new ModelAndView();
        if (!Validator.login(login)) {
            modelAndView.setViewName(REGISTER_PAGE);
            modelAndView.addObject(INFO, "Wrong login format");
            return modelAndView;
        }
        if (!Validator.password(password)) {
            modelAndView.setViewName(REGISTER_PAGE);
            modelAndView.addObject(INFO, "Wrong password format");
            return modelAndView;
        }
        if (!Validator.fullName(fullName)) {
            modelAndView.setViewName(REGISTER_PAGE);
            modelAndView.addObject(INFO, "Wrong full name format");
            return modelAndView;
        }
        if (iUserDAO.findUserByLoginAndPassword(login, password) == null) {
            User user = new User(fullName, login, password);
            iUserDAO.insertUser(user);
            modelAndView.setViewName(MAIN);
            modelAndView.addObject(INFO, "User " + user.toString() + " added");
        } else {
            modelAndView.setViewName(REGISTER_PAGE);
            modelAndView.addObject(INFO, "User with same login already exists");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView(AUTHORIZATION_PAGE, USER, new User());
    }

    @RequestMapping(value = "/authorization.do", method = RequestMethod.POST)
    public ModelAndView authorization(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        User newUser = iUserDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword());

        if (newUser != null) {
            modelAndView.setViewName(USER_PAGE);
            modelAndView.addObject(USER, newUser);
        } else {
            modelAndView.setViewName(AUTHORIZATION_PAGE);
            modelAndView.addObject(INFO, "Wrong login or password");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logOut.do", method = RequestMethod.GET)
    public ModelAndView logout() {
        return new ModelAndView(MAIN);
    }
}
