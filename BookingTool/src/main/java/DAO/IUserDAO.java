package DAO;

import Model.LocalModel.User;

import java.util.List;

public interface IUserDAO {
    int insertUser(User user);

    void deleteUser(User user);

    User findUser(Integer id);

    List getAllUsers();

}