package Catalog.DAO.Impl.MySQL;

import Catalog.DAO.Repository.CartoonRepository;
import Catalog.DAO.Service.ICartoonService;
import Catalog.Entity.Cartoon;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MySQLCartoonImpl implements ICartoonService {
    @Autowired
    private CartoonRepository cartoonRepository;

    public void addCartoon(Cartoon cartoon) {
        cartoonRepository.saveAndFlush(cartoon);
    }

    public void deleteCartoon(long id) {
        cartoonRepository.delete(id);
    }

    public Cartoon getCartoonById(long id) {
        return cartoonRepository.findOne(id);
    }

    public List<Cartoon> getCartoonsByUserId(long user_id) {
        return cartoonRepository.getCartoonsByUserId(user_id);
    }
}
