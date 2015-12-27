package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 21.12.2015.
 */
public class InsertWagon implements ICommand {
    private String name = "insertwagon";

    public void execute(int seatNumber, String lastName, String firstName) {
    }

    public void insertWagon(int number, String type) {
        Wagon wagon = new Wagon();
        wagon.setNumber(number);
        wagon.setWagonType(type);
        Factory.getMySQLWagonDAO().insertWagon(wagon);
    }

    public void printHelp() {
        System.out.println("- " + name + " -- insert new wagon");
        System.out.println("     " + name + " \"wagonNumber\" \"wagonType\"");
    }
}
