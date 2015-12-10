package Controller;

import Model.ConsoleCommands.*;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 02.12.2015.
 */
public class CommandController {
    private static boolean checker = false;
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
                }
            } else if (parsedCommand.parts != null) {
                if (parsedCommand.command.equals("buy")) {
                    if (seatCheck(parsedCommand.seatNumber)){
                        new BuyTicket().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                    }
                } else if (parsedCommand.command.equals("remove")) {
                    new RemoveUserFromXmlFile().remove(parsedCommand.id);
                }else {
                    System.out.println("Wrong command.");
                }
            }
        } else {
            System.out.println("Wrong command.");
        }
    }
    public static boolean seatCheck(int seatNumber){
        for (int i = 0; i < new Wagon().seatChecker.length; i++){
            if (Wagon.seatChecker[i] == seatNumber){
                checker = true;
                return checker;
            } else {
                checker = false;
            }
        }
        System.out.println("Wrong seat number");
        return checker;
    }

}
