package Catalog.Controller;

import Catalog.DAO.Service.ISoundService;
import Catalog.Entity.Enum.GenreSound;
import Catalog.Entity.Lists.ListGenreSound;
import Catalog.Entity.Sound;
import Catalog.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SoundController implements AppStaticValues {
    @Autowired
    private ISoundService iSoundService;

    @RequestMapping(value = "addSound", method = RequestMethod.GET)
    public ModelAndView addSound() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_ADD_SOUND);
        modelAndView.addObject(LIST_OF, ListGenreSound.getSoundGenres());
        modelAndView.addObject(SOUND, new Sound());

        return modelAndView;
    }

    @RequestMapping(value = "addSound", method = RequestMethod.POST)
    public ModelAndView addSound(@ModelAttribute Sound sound, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        sound.setUser(user);

        iSoundService.addSound(sound);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_GET_SOUNDS);
        modelAndView.addObject(INFO, sound.toString() + "added");
        modelAndView.addObject(LIST_OF, iSoundService.getSoundsByUserId(user.getId()));

        return modelAndView;
    }

    @RequestMapping(value = "getSounds", method = RequestMethod.GET)
    public ModelAndView getSounds(HttpSession session) {

        User user = (User) session.getAttribute(USER);
        return new ModelAndView(PAGE_GET_SOUNDS, LIST_OF, iSoundService.getSoundsByUserId(user.getId()));
    }

    @RequestMapping(value = "getSoundsByGenre", method = RequestMethod.GET)
    public ModelAndView getSoundsByGenre() {
        return new ModelAndView(PAGE_GET_SOUNDS_BY_GENRE, LIST_OF, ListGenreSound.getSoundGenres());
    }

    @RequestMapping(value = "getSoundsByGenre", method = RequestMethod.POST)
    public ModelAndView getSoundsByGenre(@RequestParam(value = SOUND_GENRE) String genre) {
        return new ModelAndView(PAGE_GET_SOUNDS, LIST_OF, iSoundService.getSoundsByGenre(GenreSound.valueOf(genre)));
    }
}
