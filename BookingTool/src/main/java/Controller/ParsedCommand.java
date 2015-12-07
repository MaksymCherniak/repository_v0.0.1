package Controller;

/**
 * Created by Max on 02.12.2015.
 */
public class ParsedCommand {
    public String command;
    public int seatNumber;
    public String lastName;
    public String firstName;
    public String[] parts;
    public String id;

    public boolean setCommand(String fullLine) {
        if (!fullLine.contains(" ")) {
            command = fullLine;
            return true;
        } else {
            parts = fullLine.split(" ");
        }
        if (parts != null && parts.length == 4){
            command = parts[0];
            seatNumber = Integer.parseInt(parts[1]);
            lastName = parts[2];
            firstName = parts[3];
            return true;
        } else if (parts != null && parts.length == 2) {
            command = parts[0];
            id = parts[1];
            return true;
        } else {
            return false;
        }
    }
}
