package Catalog.DAO.Repository;

import Catalog.Controller.AppStaticValues;
import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT f FROM Film f WHERE user_id LIKE :user_id")
    List<Film> getFilmsByUserId(@Param(AppStaticValues.USER_ID) long user_id);

    @Query("SELECT f FROM Film f WHERE genreFilm LIKE :genreFilm")
    List<Film> getFilmsByGenre(@Param(AppStaticValues.FILM_GENRE) GenreFilm genreFilm);
}
