package Controller;

import Model.Commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandController {
    private static Logger log = Logger.getLogger(CommandController.class.getName());
    private String command;
    private String[] parts;
    private static Map<String, ICommand> commandMap = new HashMap<String, ICommand>();

    static {
        commandMap.put("add", new AddBook());
        commandMap.put("exit", new Exit());
        commandMap.put("help", new Help());
        commandMap.put("delete", new DeleteBook());
        commandMap.put("updatea", new UpdateAuthor());
        commandMap.put("updated", new UpdateDescription());
        commandMap.put("updateg", new UpdateGenre());
        commandMap.put("updatep", new UpdatePrice());
        commandMap.put("updatepd", new UpdatePublishDate());
        commandMap.put("updatet", new UpdateTitle());
        commandMap.put("printall", new PrintAllBooks());
    }

    public void searchCommand(String fullLine) {
        parts = fullLine.split(" ");
        command = parts[0];
        ICommand cmnd = commandMap.get(command);
        if (cmnd == null) {
            log.info("Wrong command.");
        } else {
            cmnd.execute(fullLine);
        }
    }
}
