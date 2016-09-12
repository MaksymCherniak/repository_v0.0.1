package tasklist.dao.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasklist.dao.services.ITaskService;
import tasklist.entity.Task;
import tasklist.entity.TaskListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TaskImpl implements ITaskService {
    private static Logger log = Logger.getLogger(TaskImpl.class.getName());
    private Map<String, Task> taskMap = new HashMap<>();
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    @Override
    public void addTask(Task task) {
        if (taskMap.containsKey(task.getName())) {
            taskMap.get(task.getName()).addUserMemory(task.getUsedMemory());
        } else {
            taskMap.put(task.getName(), task);
        }
    }

    @Override
    public ObservableList<Task> getAllFromConsole() {
        ObservableList<Task> list = FXCollections.observableArrayList(taskMap.values());
        list.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getUsedMemory().compareTo(o2.getUsedMemory());
            }
        });
        return list;
    }

    @Override
    public void saveFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TaskListWrapper wrapper = new TaskListWrapper();
            wrapper.setTasks(getAllFromConsole());

            m.marshal(wrapper, file);
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Error. Could not save data to file:\n" + file.getPath());
        }

    }

    @Override
    public void loadFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(TaskListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            TaskListWrapper wrapper = (TaskListWrapper) um.unmarshal(file);

            tasks.clear();
            tasks.addAll(wrapper.getTasks());
        } catch (Exception e) {
            log.warning("Error. Could not load data from file:\n" + file.getPath());
        }
    }

    @Override
    public ObservableList<Task> getAllFromFile() {
        return tasks;
    }
}
