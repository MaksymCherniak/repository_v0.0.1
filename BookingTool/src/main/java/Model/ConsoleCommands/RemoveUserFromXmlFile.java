package Model.ConsoleCommands;

import Model.LocalModel.User;
import Model.LocalModel.XmlUserDAO;

/**
 * Created by Max on 08.12.2015.
 */
public class RemoveUserFromXmlFile implements ICommand {
    private static String name = "remove";
    public void execute(int seatNumber, String lastName, String firstName) { }
    public void remove(String id){
        if (new XmlUserDAO().checkId(id)) {
            new XmlUserDAO().deleteUser(id);
        } else {
            System.out.println("User with id \"" + id + "\" not found.");
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- remove user from xml file");
        System.out.println("     " + name + " \"id\"");
    }
}
