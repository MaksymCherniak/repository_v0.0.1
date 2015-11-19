package View;

import java.io.*;

import Controller.Controller;
import Controller.ParsedCommand;

import javax.naming.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by Max on 18.11.2015.
 */
public class main {
    public static void main(String[] args) throws IOException{
        Controller controller = new Controller();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            String line = reader.readLine();
            controller.setFullLine(line);
            controller.searchCommand();
        }while (true);
    }
}