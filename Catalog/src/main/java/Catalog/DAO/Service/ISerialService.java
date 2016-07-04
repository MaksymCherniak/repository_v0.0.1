package Catalog.DAO.Service;

import Catalog.Entity.Enum.GenreFilm;
import Catalog.Entity.Serial;

import java.util.List;

public interface ISerialService {
    void addSerial(Serial serial);

    void deleteSerial(long id);

    Serial getSerialById(long id);

    List<Serial> getSerialsByUserId(long user_id);

    List<Serial> getSerialsByGenre(GenreFilm genreFilm);
}
