package cards.dao.service;

import cards.entity.User;

public interface IUserService {
    boolean insertUser(User user);

    User findUserByLoginAndPassword(String login, String password);

    User findUserById(long id);

    void deleteUser(long id);
}
