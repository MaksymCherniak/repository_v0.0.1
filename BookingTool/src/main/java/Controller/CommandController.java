package Controller;

import Model.ConsoleCommands.*;
import Model.LocalModel.AvailabilitySeats;
import Model.LocalModel.LocalData;

import java.util.Iterator;
import java.util.Map;

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
                    new PrintSeats().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                } else if (parsedCommand.command.equals("printxml")){
                    new PrintXmlFile().execute(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
                }
            } else if (parsedCommand.parts != null) {
                if (parsedCommand.command.equals("buy")) {
                    seatCheck(parsedCommand.seatNumber, parsedCommand.lastName, parsedCommand.firstName);
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
    private void seatCheck(int seatNumber, String lastName, String firstName){
        for (int i = 0; i < LocalData.seatChecker.length; i++){
            if (LocalData.seatChecker[i] == seatNumber){
                checker = true;
                break;
            } else {
                checker = false;
            }
        }
        if (checker){
            Iterator<Map.Entry<Integer, AvailabilitySeats>> iterator =
                    LocalData.collectionOfAvailabilitySeats.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Integer, AvailabilitySeats> pair = iterator.next();
                if (pair.getKey().equals(seatNumber)){
                    if (pair.getValue().equals(AvailabilitySeats.FREE)){
                        new BuyTicket().execute(seatNumber, lastName, firstName);
                    } else {
                        System.out.println("Seat " + seatNumber + " is occupied, please try to buy another seat.");
                    }
                }
            }
        } else {
            System.out.println("We haven't seat number " + seatNumber + " at this bus, please try to buy another seat.");
        }
    }
}
