package BookingTool.DAO.Service;

import BookingTool.Entity.User;

import java.util.List;

public interface IUserService {
    boolean insertUser(User user);

    boolean deleteUser(User user);

    User getUserById(Long id);

    User getUserByLoginAndPassword(String login, String password);
}