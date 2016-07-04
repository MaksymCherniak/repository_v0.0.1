package Catalog.DAO.Service;

import Catalog.Entity.Enum.GenreSound;
import Catalog.Entity.Sound;

import java.util.List;

public interface ISoundService {
    void addSound(Sound sound);

    void deleteSound(long id);

    Sound getSoundById(long id);

    List<Sound> getSoundsByUserId(long user_id);

    List<Sound> getSoundsByGenre(GenreSound genreSound);
}
