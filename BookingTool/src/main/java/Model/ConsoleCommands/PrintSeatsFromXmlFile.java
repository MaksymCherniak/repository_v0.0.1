package Model.ConsoleCommands;

import DAO.XmlWagonDAO;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 02.12.2015.
 */
public class PrintSeatsFromXmlFile implements ICommand{
    private String name = "print";
    public void execute(int seatNumber, String lastName, String firstName) {
        new XmlWagonDAO(new Wagon()).printWagon();
    }
    public void printHelp() {
        System.out.println("- " + name + " -- print all seats");
    }
}
