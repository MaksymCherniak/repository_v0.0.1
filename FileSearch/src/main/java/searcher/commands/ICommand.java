package searcher.commands;

import searcher.mainClasses.AppStaticValues;

public interface ICommand extends AppStaticValues {
    void execute(String fullLine);

    String printHelp();
}
