package View;

import Controller.CommandController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandController commandController = new CommandController();
        System.out.println("Welcome. Please enter some command.");
        do {
            commandController.searchCommand(reader.readLine());
        } while (true);
    }
}
