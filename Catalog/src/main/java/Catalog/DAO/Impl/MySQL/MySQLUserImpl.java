package Catalog.DAO.Impl.MySQL;

import Catalog.DAO.Repository.UserRepository;
import Catalog.DAO.Service.IUserService;
import Catalog.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class MySQLUserImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
