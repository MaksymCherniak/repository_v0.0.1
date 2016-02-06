package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IWagonDAO;
import BookingTool.Model.LocalModel.Seat;
import BookingTool.Model.LocalModel.Wagon;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class PrintWagon implements ICommand {
    private static Logger log = Logger.getLogger(PrintWagon.class.getName());
    private final static String name = "printwagon";
    private String[] parts;

    public void execute(String fullLine, GenericXmlApplicationContext ctx) {
        parts = fullLine.split(" ");
        if (parts.length == 2) {
            IWagonDAO iWagonDAO = ctx.getBean("MySQLWagonDAO", IWagonDAO.class);
            Wagon wagon = iWagonDAO.findWagon(Integer.parseInt(parts[1]));
            if (wagon != null) {
                List<Seat> list = iWagonDAO.getAllSeats(wagon);
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