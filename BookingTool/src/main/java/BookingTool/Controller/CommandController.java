package BookingTool.Controller;

import BookingTool.Model.ConsoleCommands.*;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandController {
    private static Logger log = Logger.getLogger(CommandController.class.getName());
    private String command;
    private String[] parts;
    private static Map<String, ICommand> mapOfCommands = new HashMap<String, ICommand>();

    static {
        mapOfCommands.put("help", new Help());
        mapOfCommands.put("exit", new Exit());
        mapOfCommands.put("print", new PrintAllWagons());
        mapOfCommands.put("printt", new PrintAllTickets());
        mapOfCommands.put("printdbu", new PrintAllUsers());
        mapOfCommands.put("buy", new BuyTicket());
        mapOfCommands.put("printwagon", new PrintWagon());
        mapOfCommands.put("remove", new RemoveTicket());
        mapOfCommands.put("insertwagon", new InsertWagon());
        mapOfCommands.put("printroute", new PrintRoute());
        mapOfCommands.put("insertroute", new InsertRoute());
        mapOfCommands.put("create", new CreateUser());
    }

    public void searchCommand(String fullLine) {
        parts = fullLine.split(" ");
        command = parts[0];
        ICommand cmnd = mapOfCommands.get(command);
        if (cmnd == null) {
            log.warning("Wrong command");
        } else {
            cmnd.execute(fullLine);
        }
    }
}