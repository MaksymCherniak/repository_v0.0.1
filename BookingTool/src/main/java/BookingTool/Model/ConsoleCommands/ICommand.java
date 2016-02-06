package BookingTool.Model.ConsoleCommands;

import org.springframework.context.support.GenericXmlApplicationContext;

public interface ICommand {
    void execute(String fullLine, GenericXmlApplicationContext ctx);

    void printHelp();
}