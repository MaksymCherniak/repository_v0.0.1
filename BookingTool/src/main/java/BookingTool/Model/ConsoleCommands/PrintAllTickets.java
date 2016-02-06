package BookingTool.Model.ConsoleCommands;

import BookingTool.DAO.ITicketDAO;
import BookingTool.Model.LocalModel.Ticket;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class PrintAllTickets implements ICommand {
    private static Logger log = Logger.getLogger(PrintAllTickets.class.getName());
    private final static String name = "printt";
    private String[] parts;

    public void execute(String fullLine, GenericXmlApplicationContext ctx) {
        parts = fullLine.split(" ");
        if (parts.length == 1) {
            List<Ticket> list = ctx.getBean("MySQLTicketDAO", ITicketDAO.class).getAllTickets();
            for (Ticket ticket : list) {
                System.out.println(ticket.toString());
            }
        } else {
            log.warning("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print all tickets");
    }
}