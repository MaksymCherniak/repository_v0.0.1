package Catalog.Controller;

import Catalog.DAO.Service.ISerialService;
import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Lists.ListCountry;
import Catalog.Entity.Lists.ListGenreFilm;
import Catalog.Entity.Serial;
import Catalog.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SerialController implements AppStaticValues {
    @Autowired
    private ISerialService iSerialService;

    @RequestMapping(value = "addSerial", method = RequestMethod.GET)
    public ModelAndView addSerial() {
        List<GenreFilm> listOfGenres = ListGenreFilm.getFilmGenres();

        List<String> listOfCountries = ListCountry.getCountries();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_ADD_SERIAL);
        modelAndView.addObject(COUNTRIES, listOfCountries);
        modelAndView.addObject(LIST_OF, listOfGenres);
        modelAndView.addObject(SERIAL, new Serial());

        return modelAndView;
    }

    @RequestMapping(value = "addSerial", method = RequestMethod.POST)
    public ModelAndView addSerial(@ModelAttribute Serial serial, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        serial.setUser(user);

        iSerialService.addSerial(serial);
        List<Serial> listOfSerials = iSerialService.getSerialsByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_SERIALS);
        modelAndView.addObject(INFO, serial.toString() + "added");
        modelAndView.addObject(LIST_OF, listOfSerials);

        return modelAndView;
    }

    @RequestMapping(value = "getSerials", method = RequestMethod.GET)
    public ModelAndView getSerials(HttpSession session) {
        User user = (User) session.getAttribute(USER);
        List<Serial> listOfSerials = iSerialService.getSerialsByUserId(user.getId());
        return new ModelAndView(PAGE_GET_SERIALS, LIST_OF, listOfSerials);
    }

    @RequestMapping(value = "getSerialsByGenre", method = RequestMethod.GET)
    public ModelAndView getSerialsByGenre() {
        return new ModelAndView(PAGE_GET_SERIALS_BY_GENRE, LIST_OF, ListGenreFilm.getFilmGenres());
    }

    @RequestMapping(value = "getSerialsByGenre", method = RequestMethod.POST)
    public ModelAndView getSerialsByGenre(@RequestParam(value = FILM_GENRE) String genre) {
        return new ModelAndView(PAGE_GET_SERIALS, LIST_OF, iSerialService.getSerialsByGenre(GenreFilm.valueOf(genre)));
    }
}
