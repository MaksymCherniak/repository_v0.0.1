package PhoneBook.DAO.Impl;

import PhoneBook.DAO.Repository.UserRepository;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class MySQLUserDAO implements IUserDAO{
    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public User findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User findUserById(long id) {
        return userRepository.findOne(id);
    }
}
