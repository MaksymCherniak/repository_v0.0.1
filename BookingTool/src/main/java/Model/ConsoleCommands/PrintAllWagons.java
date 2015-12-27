package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Wagon;

import java.util.List;

/**
 * Created by Max on 22.12.2015.
 */
public class PrintAllWagons implements ICommand {
    private static String name = "print";

    public void execute(int seatNumber, String lastName, String firstName) {
        List<Wagon> list = Factory.getMySQLWagonDAO().getAllWagons();
        for (Wagon wagon : list) {
            System.out.println(wagon.toString());
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all wagons");
    }
}
