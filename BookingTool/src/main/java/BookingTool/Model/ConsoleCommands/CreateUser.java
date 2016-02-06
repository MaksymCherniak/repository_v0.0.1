package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IUserDAO;
import BookingTool.Model.LocalModel.User;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.logging.Logger;

/**
 * Created by Max on 06.02.2016.
 */
public class CreateUser implements ICommand {
    private static Logger log = Logger.getLogger(CreateUser.class.getName());
    private final static String name = "create";
    private String[] parts;

    public void execute(String fullLine, GenericXmlApplicationContext ctx) {
        parts = fullLine.split(" ");
        if (parts.length == 5) {

            User user = new User(parts[1], parts[2], parts[3], parts[4]);

            IUserDAO iUserDAO = ctx.getBean("MySQLUserDAO", IUserDAO.class);
            iUserDAO.insertUser(user);
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- create new user");
    }
}
