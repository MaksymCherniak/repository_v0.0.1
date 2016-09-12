package tasklist.dao.services;

import javafx.collections.ObservableList;
import tasklist.entity.Task;

import java.io.File;

public interface ITaskService {

    void addTask(Task task);

    ObservableList<Task> getAllFromConsole();

    ObservableList<Task> getAllFromFile();

    void saveFile(File file);

    void loadFile(File file);
}
