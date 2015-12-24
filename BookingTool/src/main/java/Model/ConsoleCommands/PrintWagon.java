package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Seat;
import Model.LocalModel.Wagon;

import java.util.List;

/**
 * Created by Max on 22.12.2015.
 */
public class PrintWagon implements ICommand{
    private String name = "printwagon";
    public void execute(int wagonNumber, String lastName, String firstName) {
        List<Seat> list = Factory.getInstance().getMySQLWagonDAO().getAllSeats(wagonNumber);
        for (Seat seat : list){
            System.out.println(seat.toString());
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print wagon");
        System.out.println("     " + name + " \"wagonNumber\"");
    }
}
