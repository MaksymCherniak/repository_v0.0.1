package DAO;

import Model.LocalModel.User;

import java.util.List;

/**
 * Created by Max on 01.12.2015.
 */
public interface IUserDAO {
    int insertUser(User user);
    void deleteUser(String id);
    List getAllUsers();

}
