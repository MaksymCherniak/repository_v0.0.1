package cards.dao.impl;

import cards.dao.repository.IUserRepository;
import cards.dao.service.IUserService;
import cards.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserImpl implements IUserService {
    private static Logger log = Logger.getLogger(MySQLUserImpl.class.getName());
    @Autowired
    private IUserRepository iUserRepository;

    public boolean insertUser(User user) {
        try {
            if (findUserById(user.getId()) == null) {
                iUserRepository.saveAndFlush(user);
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public User findUserByLoginAndPassword(String login, String password) {
       // return iUserRepository.findByLoginAndPassword(login, password);
        return null;
    }

    public User findUserById(long id) {
        return iUserRepository.findOne(id);
    }

    public void deleteUser(long id) {
        iUserRepository.delete(id);
    }
}
