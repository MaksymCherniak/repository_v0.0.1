package View;

import Controller.CommandController;
import Model.LocalModel.LocalData;
import Model.LocalModel.XmlUserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Max on 01.12.2015.
 */
public class ConsoleView {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandController commandController = new CommandController();
        new LocalData().create();
        new XmlUserDAO().setIndex();
        System.out.println("Welcome. Please type some command.");
        do{
            String line = reader.readLine();
            commandController.searchCommand(line);
        }while (true);
    }
}
