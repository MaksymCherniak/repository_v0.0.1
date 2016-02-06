package BookingTool.View;

import BookingTool.Controller.CommandController;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {
    public static void main(String[] args) throws IOException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/springConfig.xml");
        ctx.refresh();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandController commandController = new CommandController();
        System.out.println("Welcome. Please enter some command.");
        do {
            commandController.searchCommand(reader.readLine(), ctx);
        } while (true);
    }
}