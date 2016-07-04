package Catalog.DAO.Repository;

import Catalog.Controller.AppStaticValues;
import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {
    @Query("SELECT s FROM Serial s WHERE user_id LIKE :user_id")
    List<Serial> getSerialsByUserId(@Param(AppStaticValues.USER_ID) long user_id);

    @Query("SELECT c FROM Serial c WHERE c.genreFilm LIKE :genreFilm")
    List<Serial> getSerialsByGenre(@Param(AppStaticValues.FILM_GENRE) GenreFilm genreFilm);
}
