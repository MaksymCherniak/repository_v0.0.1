package BookingTool.DAO.Service;

import BookingTool.Model.LocalModel.User;

import java.util.List;

public interface IUserDAO {
    boolean insertUser(User user);

    boolean deleteUser(User user);

    User findUserById(Long id);

    List getAllUsers();
}