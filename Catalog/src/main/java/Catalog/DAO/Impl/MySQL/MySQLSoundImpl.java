package Catalog.DAO.Impl.MySQL;

import Catalog.DAO.Repository.SoundRepository;
import Catalog.DAO.Service.ISoundService;
import Catalog.Entity.Enum.GenreSound;
import Catalog.Entity.Sound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MySQLSoundImpl implements ISoundService {
    @Autowired
    private SoundRepository soundRepository;

    public void addSound(Sound sound) {
        soundRepository.saveAndFlush(sound);
    }

    public void deleteSound(long id) {
        soundRepository.delete(id);
    }

    public Sound getSoundById(long id) {
        return soundRepository.findOne(id);
    }

    public List<Sound> getSoundsByUserId(long user_id) {
        return soundRepository.getSoundsByUserId(user_id);
    }

    public List<Sound> getSoundsByGenre(GenreSound genreSound) {
        return soundRepository.getSoundsByGenre(genreSound);
    }
}
