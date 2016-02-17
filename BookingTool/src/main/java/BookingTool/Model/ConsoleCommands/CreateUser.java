package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IUserDAO;
import BookingTool.Model.LocalModel.User;

import java.util.logging.Logger;

/**
 * Created by Max on 06.02.2016.
 */
public class CreateUser implements ICommand {
    private IUserDAO iUserDAO;
    private static Logger log = Logger.getLogger(CreateUser.class.getName());
    private final static String name = "create";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 5) {
            User user = new User(parts[1], parts[2], parts[3], parts[4]);
            iUserDAO.insertUser(user);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- create new user");
    }

    public IUserDAO getiUserDAO() {
        return iUserDAO;
    }

    public void setiUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }
}
