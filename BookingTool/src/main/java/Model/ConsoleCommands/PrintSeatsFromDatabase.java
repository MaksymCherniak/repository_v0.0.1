package Model.ConsoleCommands;

import DAO.MySQLWagonDAO;

import java.util.List;

/**
 * Created by Max on 16.12.2015.
 */
public class PrintSeatsFromDatabase implements ICommand {
    private String name = "printdb";
    public void execute(int seatNumber, String lastName, String firstName) {
        List<String> list = new MySQLWagonDAO().getAllSeats();
        for (String s : list){
            System.out.println(s);
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- print all seats from MySQL");
    }
}
