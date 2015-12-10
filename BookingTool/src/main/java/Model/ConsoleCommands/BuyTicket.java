package Model.ConsoleCommands;

import DAO.XmlWagonDAO;
import Model.LocalModel.User;
import DAO.XmlUserDAO;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 02.12.2015.
 */
public class BuyTicket implements ICommand {
    private static String name = "buy";

    public void execute(int seatNumber, String lastName, String firstName) {
        new XmlUserDAO().setIndex();
        User user = new User(lastName, firstName);
        Wagon wagon = new Wagon();
        XmlWagonDAO wagonXml = new XmlWagonDAO(wagon);
        if (wagonXml.checkSeatAvailable(seatNumber)){
            new XmlUserDAO().insertUser(user);
            wagonXml.insertSeat(seatNumber, user.getIndex());
            System.out.println("Thanks for your order. Your seat is number " + seatNumber + ".");
        } else {
            System.out.println("Seat number " + seatNumber + " is occupied");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- buy some ticket.");
        System.out.println("     " + name + " \"seatNumber\" \"Your surname\" \"Your name\"");
    }
}
