package Model.LocalModel;

import Model.DAOModel.IUserDAO;

import java.util.List;
import java.util.Map;

/**
 * Created by Max on 01.12.2015.
 */
public class User implements IUserDAO {
    private String firstName;
    private String lastName;
    public User create(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return null;
    }
    public String getFullName(){
        return lastName + " " + firstName;
    }

    public User delete(User user) {
        return null;
    }
    public User read(int key) {
        return null;
    }
    public User update(User user) {
        return null;
    }
    public List<User> getAll() {
        return null;
    }

}
