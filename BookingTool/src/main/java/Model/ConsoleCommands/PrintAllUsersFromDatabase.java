package Model.ConsoleCommands;

import DAO.Factory;
import DAO.MySQLUserDAO;
import Model.LocalModel.User;
import com.sun.prism.impl.BaseMesh;

import java.util.List;

/**
 * Created by Max on 16.12.2015.
 */
public class PrintAllUsersFromDatabase implements ICommand {
    private String name = "printdbu";
    public void execute(int seatNumber, String lastName, String firstName) {
        List<User> list = Factory.getInstance().getMySQLUserDAO().getAllUsers();
        for (User user : list){
            System.out.println(user.toString());
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- print all users from MySQL");
    }
}
