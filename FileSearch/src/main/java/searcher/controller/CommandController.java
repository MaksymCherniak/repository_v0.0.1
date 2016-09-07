package searcher.controller;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import searcher.commands.ICommand;
import searcher.mainClasses.AppStaticValues;
import searcher.mainClasses.ApplicationContext;

import org.apache.log4j.Logger;

public class CommandController implements AppStaticValues {
    private static Logger log = Logger.getLogger(CommandController.class.getName());
    private String command;
    private String[] parts;

    public void searchCommand(String fullLine) {
        parts = fullLine.split(SPACE);
        command = parts[0];
        try {
            ICommand cmnd = ApplicationContext.getCtx().getBean(command, ICommand.class);
            cmnd.execute(fullLine);
        } catch (NoSuchBeanDefinitionException exception) {
            log.warn(WRONG_COMMAND);
        }
    }
}