package Catalog.DAO.Service;

import Catalog.Entity.Cartoon;

import java.util.List;

public interface ICartoonService {
    void addCartoon(Cartoon cartoon);

    void deleteCartoon(long id);

    Cartoon getCartoonById(long id);

    List<Cartoon> getCartoonsByUserId(long user_id);
}
