package Model.ConsoleCommands;

import DAO.*;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 02.12.2015.
 */
public class BuyTicket implements ICommand {
    private static String name = "buy";

    public void execute(int seatNumber, String lastName, String firstName) {
        new XmlUserDAO().setIndex();
        User user = new User(lastName, firstName);
        XmlWagonDAO wagonXml = new XmlWagonDAO();
        MySQLWagonDAO mySQLWagonDAO = new MySQLWagonDAO();
        if (wagonXml.checkSeatAvailable(seatNumber) && mySQLWagonDAO.checkSeatAvailable(seatNumber)){
            new XmlUserDAO().insertUser(user);
            new MySQLUserDAO().insertUser(user);
            new UserDAOImpl().insertUser(user);
            wagonXml.insertSeat(seatNumber, user);
            mySQLWagonDAO.insertSeat(seatNumber, user);
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
