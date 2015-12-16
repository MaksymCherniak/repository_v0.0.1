package Model.ConsoleCommands;

import DAO.MySQLUserDAO;

import java.util.List;

/**
 * Created by Max on 16.12.2015.
 */
public class PrintAllUsersFromDatabase implements ICommand {
    private String name = "printdbu";
    public void execute(int seatNumber, String lastName, String firstName) {
        List<String> list = new MySQLUserDAO().getAllUsers();
        for (String s : list){
            System.out.println(s);
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- print all users from MySQL");
    }
}
