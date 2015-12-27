package Controller;

import Model.ConsoleCommands.*;
import Model.LocalModel.Wagon;

import java.util.List;

/**
 * Created by Max on 02.12.2015.
 */
public class CommandController {
    private boolean checker = false;

    public void searchCommand(String fullLine) {
        ParsedCommand parsedCommand = new ParsedCommand();
        if (parsedCommand.setCommand(fullLine)) {
            if (parsedCommand.parts == null) {
                switch (parsedCommand.command) {
                    case "help":
                        new Help().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                        break;
                    case "exit":
                        new Exit().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                        break;
                    case "print":
                        new PrintAllWagons().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                        break;
                    case "printt":
                        new PrintAllTickets().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                        break;
                    case "printdbu":
                        new PrintAllUsersFromDatabase().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                        break;
                }
            } else {
                switch (parsedCommand.command) {
                    case "buy":
                        if (seatCheck(parsedCommand.seatNumber)) {
                            new BuyTicket().buyTicket(parsedCommand.wagonNumber, parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                        }
                        break;
                    case "printwagon":
                        new PrintWagon().execute(Integer.parseInt(parsedCommand.id), parsedCommand.lastName, parsedCommand.firstName);
                        break;
                    case "remove":
                        new RemoveUser().remove(parsedCommand.id);
                        break;
                    case "insertwagon":
                        new InsertWagon().insertWagon(parsedCommand.wagonNumber, parsedCommand.wagonType);
                        break;
                    default:
                        System.out.println("Wrong command.");
                        break;
                }
            }
        } else {
            System.out.println("Wrong command.");
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
