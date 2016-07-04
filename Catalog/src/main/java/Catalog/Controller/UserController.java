package Catalog.Controller;

import Catalog.DAO.Service.IUserService;
import Catalog.Entity.User;
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
    private IUserService iUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute User user, HttpSession session) {
        session.setAttribute(USER, iUserService.getUserByLoginAndPassword(user.getLogin(), user.getPassword()));
        return PAGE_MAIN;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute User user, HttpSession session) {
        iUserService.addUser(user);
        session.setAttribute(USER, iUserService.getUserByLoginAndPassword(user.getLogin(), user.getPassword()));
        return new ModelAndView(PAGE_MAIN, INFO, "User " + user.toString() + " added. Welcome");
    }

    @RequestMapping(value = "registerPage", method = RequestMethod.GET)
    public String registerPage() {
        return PAGE_REGISTRATION;
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String mainPage() {
        return PAGE_MAIN;
    }

    @RequestMapping(value = {"/", "logout"}, method = RequestMethod.GET)
    public ModelAndView loginPage() {
        return new ModelAndView(PAGE_LOGIN, USER, new User());
    }
}
