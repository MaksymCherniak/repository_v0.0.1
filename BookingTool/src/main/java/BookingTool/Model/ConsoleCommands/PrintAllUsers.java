package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.Service.IUserDAO;
import BookingTool.Model.LocalModel.User;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllUsers implements ICommand {
    private IUserDAO iUserDAO;
    private static Logger log = Logger.getLogger(PrintAllUsers.class.getName());
    private final static String name = "printusers";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<User> list = iUserDAO.getAllUsers();
            for (User user : list) {
                System.out.println(user.toString());
            }
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all users");
    }

    public IUserDAO getiUserDAO() {
        return iUserDAO;
    }

    public void setiUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }
}