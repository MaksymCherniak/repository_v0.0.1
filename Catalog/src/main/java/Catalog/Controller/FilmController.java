package Catalog.Controller;

import Catalog.DAO.Service.IFilmService;
import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Film;
import Catalog.Entity.Lists.ListCountry;
import Catalog.Entity.Lists.ListGenreFilm;
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
public class FilmController implements AppStaticValues {
    @Autowired
    private IFilmService iFilmService;

    @RequestMapping(value = "addFilm", method = RequestMethod.GET)
    public ModelAndView addFilm() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_ADD_FILM);
        modelAndView.addObject(COUNTRIES, ListCountry.getCountries());
        modelAndView.addObject(LIST_OF, ListGenreFilm.getFilmGenres());
        modelAndView.addObject(FILM, new Film());

        return modelAndView;
    }

    @RequestMapping(value = "addFilm", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute Film film, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        film.setUser(user);

        iFilmService.addFilm(film);
        List<Film> listOfFilms = iFilmService.getFilmsByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_FILMS);
        modelAndView.addObject(INFO, film.toString() + "added");
        modelAndView.addObject(LIST_OF, listOfFilms);

        return modelAndView;
    }

    @RequestMapping(value = "getFilms", method = RequestMethod.GET)
    public ModelAndView getFilms(HttpSession session) {
        User user = (User) session.getAttribute(USER);
        List<Film> listOfFilms = iFilmService.getFilmsByUserId(user.getId());
        return new ModelAndView(PAGE_GET_FILMS, LIST_OF, listOfFilms);
    }

    @RequestMapping(value = "getFilmsByGenre", method = RequestMethod.GET)
    public ModelAndView getFilmsByGenre() {
        return new ModelAndView(PAGE_GET_FILMS_BY_GENRE, LIST_OF, ListGenreFilm.getFilmGenres());
    }

    @RequestMapping(value = "getFilmsByGenre", method = RequestMethod.POST)
    public ModelAndView getFilmsByGenre(@RequestParam(value = FILM_GENRE) String genre) {
        return new ModelAndView(PAGE_GET_FILMS, LIST_OF, iFilmService.getFilmsByGenre(GenreFilm.valueOf(genre)));
    }
}
