package DAO;

import Model.LocalModel.User;

import java.util.List;

public interface IUserDAO {
    boolean insertUser(User user);

    boolean deleteUser(User user);

    User findUserById(Integer id);

    List getAllUsers();
}