package BookingTool.DAO.Impl;

import BookingTool.DAO.Repository.UserRepository;
import BookingTool.DAO.Service.IUserService;
import BookingTool.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLUserImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    private static Logger log = Logger.getLogger(MySQLUserImpl.class.getName());

    public MySQLUserImpl() {
    }

    public boolean insertUser(User user) {
        if (getUserByLoginAndPassword(user.getEmail(), user.getPassword()) == null) {
            userRepository.saveAndFlush(user);
            log.info("User created");
            return true;
        } else {
            log.info("User already created");
        }
        return false;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userRepository.getUserByLoginAndPassword(login, password);
    }

    public User getUserById(Long id) {
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
            if (getUserByLoginAndPassword(user.getEmail(), user.getPassword()) != null) {
                userRepository.delete(user);
                log.info("User removed");
                return true;
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }
}