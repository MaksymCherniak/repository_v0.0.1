package View;

import Controller.CommandController;
import DAO.Factory;
import Model.ConsoleCommands.InsertRoute;
import Model.LocalModel.Route;
import Model.LocalModel.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/**
 * Created by Max on 01.12.2015.
 */
public class ConsoleView {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandController commandController = new CommandController();
        System.out.println("Welcome. Please type some command.");
        do {
            commandController.searchCommand(reader.readLine());
        } while (true);
    }
}