package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.IWagonDAO;
import BookingTool.Model.LocalModel.Wagon;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllWagons implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllWagons.class.getName());
    private final static String name = "print";
    private String[] parts;

    public void execute(String fullLine, GenericXmlApplicationContext ctx) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<Wagon> list = ctx.getBean("MySQLWagonDAO", IWagonDAO.class).getAllWagons();
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
}