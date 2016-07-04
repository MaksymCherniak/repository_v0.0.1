package Catalog.DAO.Repository;

import Catalog.Controller.AppStaticValues;
import Catalog.Entity.Enum.GenreSound;
import Catalog.Entity.Sound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long> {
    @Query("SELECT s FROM Sound s WHERE user_id LIKE :user_id")
    List<Sound> getSoundsByUserId(@Param(AppStaticValues.USER_ID) long user_id);

    @Query("SELECT s FROM Sound s WHERE s.genreSound LIKE :genreSound")
    List<Sound> getSoundsByGenre(@Param(AppStaticValues.SOUND_GENRE) GenreSound genreSound);
}
