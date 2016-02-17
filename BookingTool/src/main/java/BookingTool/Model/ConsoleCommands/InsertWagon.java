package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IWagonDAO;
import BookingTool.Model.LocalModel.Wagon;

import java.util.logging.Logger;

public class InsertWagon implements ICommand {
    private IWagonDAO iWagonDAO;
    private static Logger log = Logger.getLogger(InsertWagon.class.getName());
    private final static String name = "insertwagon";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 3 && (parts[2].equalsIgnoreCase("comfortable") || parts[2].equalsIgnoreCase("compartment") ||
                parts[2].equalsIgnoreCase("economy"))) {
            Wagon wagon = new Wagon();
            wagon.setNumber(Integer.parseInt(parts[1]));
            wagon.setWagonType(parts[2]);
            iWagonDAO.insertWagon(wagon);
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- insert new wagon");
        System.out.println("     " + name + " \"wagonNumber\" \"wagonType\"");
    }

    public IWagonDAO getiWagonDAO() {
        return iWagonDAO;
    }

    public void setiWagonDAO(IWagonDAO iWagonDAO) {
        this.iWagonDAO = iWagonDAO;
    }
}