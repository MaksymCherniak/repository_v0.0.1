package Model.ConsoleCommands;

import DAO.XmlUserDAO;
import DAO.XmlWagonDAO;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 08.12.2015.
 */
public class RemoveUserFromXmlFile implements ICommand {
    private static String name = "remove";
    public void execute(int seatNumber, String lastName, String firstName) { }
    public void remove(String id){
        XmlUserDAO xmlUserDAO = new XmlUserDAO();
        if (xmlUserDAO.checkId(id)) {
            xmlUserDAO.deleteUser(id);
            new XmlWagonDAO(new Wagon()).updateWagon(Integer.parseInt(id));
        } else {
            System.out.println("User with id \"" + id + "\" not found.");
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- remove user from xml file");
        System.out.println("     " + name + " \"id\"");
    }
}
