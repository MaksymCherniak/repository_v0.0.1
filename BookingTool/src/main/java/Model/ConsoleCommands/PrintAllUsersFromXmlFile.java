package Model.ConsoleCommands;

import DAO.XmlUserDAO;

import java.util.List;

/**
 * Created by Max on 08.12.2015.
 */
public class PrintAllUsersFromXmlFile implements ICommand{
    private static String name = "printxml";
    public void execute(int seatNumber, String lastName, String firstName) {
        List<String> list = new XmlUserDAO().getAllUsers();
        for (String s : list){
            System.out.println(s);
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all users from xml file");
    }
}
