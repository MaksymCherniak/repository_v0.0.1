package Controller;

import Model.ConsoleCommands.*;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 02.12.2015.
 */
public class CommandController {
    private boolean checker = false;
    public void searchCommand(String fullLine){
        ParsedCommand parsedCommand = new ParsedCommand();
        if (parsedCommand.setCommand(fullLine)){
            if (parsedCommand.parts == null){
                if (parsedCommand.command.equals("help")){
                    new Help().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                } else if (parsedCommand.command.equals("exit")){
                    new Exit().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                } else if (parsedCommand.command.equals("print")){
                    new PrintSeatsFromXmlFile().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                } else if (parsedCommand.command.equals("printxml")){
                    new PrintAllUsersFromXmlFile().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                } else if (parsedCommand.command.equals("printdb")){
                    new PrintSeatsFromDatabase().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                } else if (parsedCommand.command.equals("printdbu")){
                    new PrintAllUsersFromDatabase().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                }
            } else if (parsedCommand.parts != null) {
                if (parsedCommand.command.equals("buy")) {
                    if (seatCheck(parsedCommand.seatNumber)){
                        new BuyTicket().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                    }
                } else if (parsedCommand.command.equals("remove")) {

                    new RemoveUser().remove(parsedCommand.id);
                }else {
                    System.out.println("Wrong command.");
                }
            }
        } else {
            System.out.println("Wrong command.");
        }
    }
    public boolean seatCheck(int seatNumber){
        int[] seats = Wagon.getSeats();
        for (int i = 0; i < seats.length; i++){
            if (seats[i] == seatNumber){
                return checker = true;
            }
        }
        System.out.println("Wrong seat number");
        return checker;
    }

}
