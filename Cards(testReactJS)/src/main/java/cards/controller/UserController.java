package cards.controller;

import cards.dao.service.IUserService;
import cards.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private static final String PAGE_MAIN = "main";

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return PAGE_MAIN;
    }
}
