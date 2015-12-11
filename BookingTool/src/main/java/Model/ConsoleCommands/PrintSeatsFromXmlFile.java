package Model.ConsoleCommands;

import DAO.XmlWagonDAO;
import java.util.List;

/**
 * Created by Max on 02.12.2015.
 */
public class PrintSeatsFromXmlFile implements ICommand{
    private String name = "print";
    public void execute(int seatNumber, String lastName, String firstName) {
        List<String> list = new XmlWagonDAO().getAllSeats();
        for (String s : list){
            System.out.println(s);
        }
    }
    public void printHelp() {
        System.out.println("- " + name + " -- print all seats");
    }
}
