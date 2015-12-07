package Model.ConsoleCommands;

import Model.LocalModel.XmlData;

/**
 * Created by Max on 08.12.2015.
 */
public class PrintXmlFile implements ICommand{
    private static String name = "printxml";
    public void execute(int seatNumber, String lastName, String firstName) {
        new XmlData().read();
    }

    public void printHelp() {
        System.out.println("- " + name + " -- print xml file");
    }
}
