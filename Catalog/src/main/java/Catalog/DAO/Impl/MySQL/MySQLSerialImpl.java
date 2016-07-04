package Catalog.DAO.Impl.MySQL;

import Catalog.DAO.Repository.SerialRepository;
import Catalog.DAO.Service.ISerialService;
import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Serial;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MySQLSerialImpl implements ISerialService {
    @Autowired
    private SerialRepository serialRepository;

    public void addSerial(Serial serial) {
        serialRepository.saveAndFlush(serial);
    }

    public void deleteSerial(long id) {
        serialRepository.delete(id);
    }

    public Serial getSerialById(long id) {
        return serialRepository.findOne(id);
    }

    public List<Serial> getSerialsByUserId(long user_id) {
        return serialRepository.getSerialsByUserId(user_id);
    }

    public List<Serial> getSerialsByGenre(GenreFilm genreFilm) {
        return serialRepository.getSerialsByGenre(genreFilm);
    }
}
