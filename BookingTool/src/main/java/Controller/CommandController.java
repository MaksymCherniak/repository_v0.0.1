package Controller;

import Model.ConsoleCommands.*;
import Model.LocalModel.Wagon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CommandController {
    private static Logger log = Logger.getLogger(CommandController.class.getName());
    private String command;
    private String[] parts;
    private boolean checker = false;
    private static Map<String, ICommand> mapOfCommands = new HashMap<>();

    static {
        mapOfCommands.put("help", new Help());
        mapOfCommands.put("exit", new Exit());
        mapOfCommands.put("print", new PrintAllWagons());
        mapOfCommands.put("printt", new PrintAllTickets());
        mapOfCommands.put("printdbu", new PrintAllUsersFromDatabase());
        mapOfCommands.put("buy", new BuyTicket());
        mapOfCommands.put("printwagon", new PrintWagon());
        mapOfCommands.put("remove", new RemoveTicket());
        mapOfCommands.put("insertwagon", new InsertWagon());
    }

    public void searchCommand(String fullLine) {
        parts = fullLine.split(" ");
        command = parts[0];
        ICommand cmnd = mapOfCommands.get(command);
        if (cmnd == null){
            log.warning("Wrong command");
        } else {
            cmnd.execute(fullLine);
        }
    }

    public boolean seatCheck(int seatNumber) {
        List<Integer> seats = Wagon.getEconomyWagonList();
        for (Integer seat : seats) {
            if (seat == seatNumber) {
                return checker = true;
            }
        }
        System.out.println("Wrong seat number");
        return checker;
    }

}