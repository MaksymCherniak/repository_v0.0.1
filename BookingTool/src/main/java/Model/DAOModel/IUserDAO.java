package Model.DAOModel;

import Model.LocalModel.User;

import java.util.List;

/**
 * Created by Max on 01.12.2015.
 */
public interface IUserDAO {
    User create(String firstName, String lastName);
    User delete(User user);
    User read(int key);
    User update(User user);
    List<User> getAll();
}
