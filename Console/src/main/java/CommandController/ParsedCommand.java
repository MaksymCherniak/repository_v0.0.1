package CommandController;

/**
 * Created by Max on 18.11.2015.
 */
public class ParsedCommand {

    public String command;
    public String args;
    public String[] parts;
    public ParsedCommand() {}

    public boolean setCommand(String fullLine) {
        if (!fullLine.contains(" ")) {
            command = fullLine;
            return true;
        } else {
            parts = fullLine.split(" ");
        }
        if (parts != null && parts.length == 2) {
            command = parts[0];
            args = parts[1];
            return true;
        } else if (parts != null && parts.length == 3) {
            command = parts[0];
            args = parts[1] + " " + parts[2];
            return true;
        } else {
            System.out.println("Wrong command. ");
            return false;
        }
    }
}