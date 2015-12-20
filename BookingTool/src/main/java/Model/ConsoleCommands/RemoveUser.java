package Model.ConsoleCommands;

import DAO.*;
import Model.LocalModel.User;

/**
 * Created by Max on 08.12.2015.
 */
public class RemoveUser implements ICommand {
    private static String name = "remove";
    public void execute(int seatNumber, String lastName, String firstName) { }
    public void remove(String id){
        User userFromXml = new XmlUserDAO().findUser(id);
        User userFromMySQL = new MySQLUserDAO().findUser(id);
        //User userFromDBUsedHibernate = new UserDAOImpl().findUser(id);
        if (userFromXml != null && userFromMySQL != null) {
            if (userFromXml.equals(userFromMySQL)) {
                new XmlUserDAO().deleteUser(userFromXml);
                new XmlWagonDAO().updateWagon(id);
                new MySQLWagonDAO().updateWagon(id);
                new MySQLUserDAO().deleteUser(userFromMySQL);
                //new UserDAOImpl().deleteUser(userFromDBUsedHibernate);
            }
        } else {
            System.out.println("User with id \"" + id + "\" not found.");
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- remove user from xml file");
        System.out.println("     " + name + " \"id\"");
    }
}
