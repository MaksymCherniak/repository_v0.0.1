package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.User;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllUsersFromDatabase implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllUsersFromDatabase.class.getName());
    private final static String name = "printdbu";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<User> list = Factory.getInstance().getMySQLUserDAO().getAllUsers();
            for (User user : list) {
                System.out.println(user.toString());
            }
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all users from MySQL");
    }
}