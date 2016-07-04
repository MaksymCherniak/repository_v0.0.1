package Catalog.Controller;

import Catalog.DAO.Service.ICartoonService;
import Catalog.Entity.Cartoon;
import Catalog.Entity.Lists.ListCountry;
import Catalog.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartoonController implements AppStaticValues {
    @Autowired
    private ICartoonService iCartoonService;

    @RequestMapping(value = "addCartoon", method = RequestMethod.GET)
    public ModelAndView addCartoon() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_ADD_CARTOON);
        modelAndView.addObject(COUNTRIES, ListCountry.getCountries());
        modelAndView.addObject(CARTOON, new Cartoon());

        return modelAndView;
    }

    @RequestMapping(value = "addCartoon", method = RequestMethod.POST)
    public ModelAndView addCartoon(@ModelAttribute Cartoon cartoon, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        cartoon.setUser(user);

        iCartoonService.addCartoon(cartoon);
        List<Cartoon> listOfCartoons = iCartoonService.getCartoonsByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_CARTOONS);
        modelAndView.addObject(INFO, cartoon.toString() + "added");
        modelAndView.addObject(LIST_OF, listOfCartoons);

        return modelAndView;
    }

    @RequestMapping(value = "getCartoons", method = RequestMethod.GET)
    public ModelAndView getCartoons(HttpSession session) {
        User user = (User) session.getAttribute(USER);
        List<Cartoon> listOfCartoons = iCartoonService.getCartoonsByUserId(user.getId());
        return new ModelAndView(PAGE_GET_CARTOONS, LIST_OF, listOfCartoons);
    }
}
