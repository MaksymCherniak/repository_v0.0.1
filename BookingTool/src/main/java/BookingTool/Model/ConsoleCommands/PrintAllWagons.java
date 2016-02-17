package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IWagonDAO;
import BookingTool.Model.LocalModel.Wagon;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllWagons implements ICommand {
    private IWagonDAO iWagonDAO;
    private static Logger log = Logger.getLogger(PrintAllWagons.class.getName());
    private final static String name = "printwagons";
    private String[] parts;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<Wagon> list = iWagonDAO.getAllWagons();
            for (Wagon wagon : list) {
                System.out.println(wagon.toString());
            }
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all wagons");
    }

    public IWagonDAO getiWagonDAO() {
        return iWagonDAO;
    }

    public void setiWagonDAO(IWagonDAO iWagonDAO) {
        this.iWagonDAO = iWagonDAO;
    }
}