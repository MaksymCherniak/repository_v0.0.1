package PhoneBook.DAO.Service;

import PhoneBook.Entity.User;

public interface IUserDAO {
    void insertUser(User user);

    User findUserByLoginAndPassword(String login, String password);

    User findUserById(long id);
}
