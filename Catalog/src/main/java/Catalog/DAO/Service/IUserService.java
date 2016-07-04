package Catalog.DAO.Service;

import Catalog.Entity.User;

public interface IUserService {
    void addUser(User user);

    void deleteUser(User user);

    User getUserById(long id);

    User getUserByLoginAndPassword(String login, String password);
}
