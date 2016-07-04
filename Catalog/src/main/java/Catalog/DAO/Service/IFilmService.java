package Catalog.DAO.Service;

import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Film;

import java.util.List;

public interface IFilmService {
    void addFilm(Film film);

    void deleteFilm(long id);

    Film getFilmById(long id);

    List<Film> getFilmsByUserId(long user_id);

    List<Film> getFilmsByGenre(GenreFilm genre);
}
