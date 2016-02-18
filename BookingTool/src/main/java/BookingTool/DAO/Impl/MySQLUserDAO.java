package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.UserRepository;
import BookingTool.DAO.Service.IUserDAO;
import BookingTool.Model.LocalModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserDAO implements IUserDAO {
    @Autowired
    private UserRepository userRepository;
    private static Logger log = Logger.getLogger(MySQLUserDAO.class.getName());

    public MySQLUserDAO() {
    }

    public boolean insertUser(User user) {
        if (findUser(user) == null) {
            userRepository.saveAndFlush(user);
            log.info("User created");
            return true;
        } else {
            log.info("User already created");
        }
        return false;
    }

    public User findUser(User user) {
        User user1 = userRepository.getUserByNameAndSurname(user.getSurname(), user.getName());
        if (user1 != null) {
            return user1;
        } else {
            log.info("User not found");
            return null;
        }
    }

    public User findUserById(Long id) {
        User user = userRepository.getOne(id);
        if (user != null) {
            return user;
        } else {
            log.info("User not found");
        }
        return null;
    }

    public boolean deleteUser(User user) {
        try {
            if (findUser(user) != null) {
                userRepository.delete(user);
                log.info("User removed");
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}