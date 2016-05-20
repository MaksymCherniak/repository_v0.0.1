package BookingTool.Controller;

import BookingTool.DAO.Service.IUserService;
import BookingTool.Entity.Seat;
import BookingTool.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController implements IControllerStaticValues {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/registration.do", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute User user, HttpSession session) {
        iUserService.insertUser(user);
        session.setAttribute(USER, iUserService.getUserByLoginAndPassword(user.getEmail(), user.getPassword()));
        return new ModelAndView(PAGE_MAIN, INFO, "User " + user.printNameAndSurname() + " created. Welcome");
    }

    @RequestMapping(value = "/authorization.do", method = RequestMethod.GET)
    public ModelAndView authorization(@ModelAttribute User user, HttpSession session) {
        Seat seat = (Seat) session.getAttribute(SEAT);
        if (seat != null) {
            User user1 = iUserService.getUserByLoginAndPassword(user.getEmail(), user.getPassword());
            session.setAttribute(USER, user1);
            return new ModelAndView(PAGE_BOOK_SEAT, SEAT, seat);
        }
        User user1 = iUserService.getUserByLoginAndPassword(user.getEmail(), user.getPassword());
        session.setAttribute(USER, user1);
        return new ModelAndView(PAGE_MAIN, INFO, user1.printNameAndSurname() + " welcome");
    }

    @RequestMapping(value = "/logOut.do", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute(USER, null);
        return PAGE_MAIN;
    }
}
