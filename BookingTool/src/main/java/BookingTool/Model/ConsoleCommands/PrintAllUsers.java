package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IUserDAO;
import BookingTool.Model.LocalModel.User;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllUsers implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllUsers.class.getName());
    private final static String name = "printdbu";
    private String[] parts;

    public void execute(String fullLine, GenericXmlApplicationContext ctx) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<User> list = ctx.getBean("MySQLUserDAO", IUserDAO.class).getAllUsers();
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