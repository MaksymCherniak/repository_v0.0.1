package PhoneBook.DAO.Impl;

import PhoneBook.DAO.Repository.UserRepository;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserDAO implements IUserDAO {
    private static Logger log = Logger.getLogger(MySQLUserDAO.class.getName());
    @Autowired
    private UserRepository userRepository;

    public boolean insertUser(User user) {
        try {
            if (findUserById(user.getId()) == null) {
                userRepository.saveAndFlush(user);
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public User findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User findUserById(long id) {
        return userRepository.findOne(id);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }
}
