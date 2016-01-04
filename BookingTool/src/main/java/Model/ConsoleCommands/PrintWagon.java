package Model.ConsoleCommands;

import DAO.Factory;
import Model.LocalModel.Seat;
import Model.LocalModel.Wagon;

import java.util.List;
import java.util.logging.Logger;

public class PrintWagon implements ICommand {
    private static Logger log = Logger.getLogger(PrintWagon.class.getName());
    private final static String name = "printwagon";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 2) {
            Wagon wagon = Factory.getMySQLWagonDAO().findWagon(Integer.parseInt(parts[1]));
            if (wagon != null) {
                List<Seat> list = Factory.getMySQLWagonDAO().getAllSeats(wagon);
                for (Seat seat : list) {
                    System.out.println(seat.toString());
                }
            } else {
                log.warning("Wagon number " + parts[1] + " not found");
            }
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print wagon");
        System.out.println("     " + name + " \"wagonNumber\"");
    }
}