package Catalog.DAO.Impl.MySQL;

import Catalog.DAO.Repository.FilmRepository;
import Catalog.DAO.Service.IFilmService;
import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Film;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MySQLFilmImpl implements IFilmService {
    @Autowired
    private FilmRepository filmRepository;

    public void addFilm(Film film) {
        filmRepository.saveAndFlush(film);
    }

    public void deleteFilm(long id) {
        filmRepository.delete(id);
    }

    public Film getFilmById(long id) {
        return filmRepository.findOne(id);
    }

    public List<Film> getFilmsByUserId(long user_id) {
        return filmRepository.getFilmsByUserId(user_id);
    }

    public List<Film> getFilmsByGenre(GenreFilm genre) {
        return filmRepository.getFilmsByGenre(genre);
    }
}
