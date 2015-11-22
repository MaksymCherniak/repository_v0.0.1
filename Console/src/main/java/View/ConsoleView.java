package View;

import java.io.*;

import Controller.CommandController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Max on 18.11.2015.
 */
public class ConsoleView {
    public static void main(String[] args) throws IOException{
        CommandController commandController = new CommandController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите начальный каталог: ");
        String directory = reader.readLine();
        commandController.setCurrentDirectory(new File(directory));
        do {
            String line = reader.readLine();
            commandController.searchCommand(line);
        }while (true);
    }
}