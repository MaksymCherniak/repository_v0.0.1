package Controller;

/**
 * Created by Max on 02.12.2015.
 */
public class ParsedCommand {
    public String command;
    public int wagonNumber;
    public int seatNumber;
    public String wagonType;
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
        if (parts != null && parts.length == 5){
            command = parts[0];
            wagonNumber = Integer.parseInt(parts[1]);
            seatNumber = Integer.parseInt(parts[2]);
            lastName = parts[3];
            firstName = parts[4];
            return true;
        } else if (parts != null && parts.length == 2) {
            command = parts[0];
            id = parts[1];
            return true;
        } else if (parts != null && parts.length == 3){
            command = parts[0];
            wagonNumber = Integer.parseInt(parts[1]);
            wagonType = parts[2];
            return true;
        } else {
            return false;
        }
    }
}
