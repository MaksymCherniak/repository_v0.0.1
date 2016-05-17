package BookingTool.DAO.Service;

import BookingTool.Entity.User;

import java.util.List;

public interface IUserService {
    boolean insertUser(User user);

    boolean deleteUser(User user);

    User findUserById(Long id);

    List getAllUsers();
}