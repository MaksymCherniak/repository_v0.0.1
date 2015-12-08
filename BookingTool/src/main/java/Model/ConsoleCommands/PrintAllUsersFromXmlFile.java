package Model.ConsoleCommands;

import Model.LocalModel.XmlUserDAO;

/**
 * Created by Max on 08.12.2015.
 */
public class PrintAllUsersFromXmlFile implements ICommand{
    private static String name = "printxml";
    public void execute(int seatNumber, String lastName, String firstName) {
        new XmlUserDAO().printAllUsers();
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all users from xml file");
    }
}
