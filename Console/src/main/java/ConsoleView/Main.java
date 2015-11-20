package ConsoleView;

import java.io.*;

import CommandController.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Max on 18.11.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        Controller controller = new Controller();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            String line = reader.readLine();
            controller.searchCommand(line);
        }while (true);
    }
}