package DAO;

import Model.LocalModel.User;

import java.util.List;

/**
 * Created by Max on 01.12.2015.
 */
public interface IUserDAO {
    int insertUser(User user);

    void deleteUser(User user);

    User findUser(String id);

    List getAllUsers();

}
