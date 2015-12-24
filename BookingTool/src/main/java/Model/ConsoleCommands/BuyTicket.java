package Model.ConsoleCommands;

import DAO.*;
import Model.LocalModel.Ticket;
import Model.LocalModel.User;
import Model.LocalModel.User2;
import Model.LocalModel.Wagon;

/**
 * Created by Max on 02.12.2015.
 */
public class BuyTicket implements ICommand {
    private static String name = "buy";

    public void execute(int seatNumber, String lastName, String firstName) {}
    public void buyTicket(int wagonNumber, int seatNumber, String lastName, String firstName){
        Factory.getInstance().getMySQLUserDAO().setIndex();
        Factory.getInstance().getMySQLTicketDAO().setIndex();
        Ticket ticket = new Ticket();
        ticket.setWagon(wagonNumber);
        ticket.setSeat(seatNumber);
        ticket.setTrain(567);
        User user = new User(lastName, firstName);
        //User2 user2 = new User2(lastName, firstName);
        ticket.setUser(user);
        MySQLWagonDAO mySQLWagonDAO = Factory.getInstance().getMySQLWagonDAO();
        if (mySQLWagonDAO.checkSeatAvailable(ticket)){
            new UserDAOImpl().insertUser(user);
            Factory.getInstance().getMySQLTicketDAO().insertTicket(ticket);
            Factory.getInstance().getMySQLUserDAO().insertUser(user);
            mySQLWagonDAO.insertSeat(ticket);
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
