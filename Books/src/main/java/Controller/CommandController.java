package Controller;

import Model.Commands.ICommand;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.logging.Logger;

public class CommandController {
    private static GenericXmlApplicationContext ctx;
    private static Logger log = Logger.getLogger(CommandController.class.getName());
    private String command;
    private String[] parts;

    static {
        ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/springConfig.xml");
        ctx.refresh();
    }

    public void searchCommand(String fullLine) {
        parts = fullLine.split(" ");
        command = parts[0];
        ICommand cmnd = ctx.getBean(command, ICommand.class);
        if (cmnd == null) {
            log.info("Wrong command.");
        } else {
            cmnd.execute(fullLine);
        }
    }
}
