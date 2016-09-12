package tasklist.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import tasklist.dao.impls.TaskImpl;
import tasklist.dao.services.ITaskService;
import tasklist.entity.Task;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    private ITaskService iTaskService = new TaskImpl();
    private ObservableList<Task> listOfTasks;
    private Desktop desktop = Desktop.getDesktop();

    @FXML
    private TableView tableTasks;
    @FXML
    private TableColumn<Task, String> columnName;
    @FXML
    private TableColumn<Task, Long> columnPID;
    @FXML
    private TableColumn<Task, Long> columnUsedMemory;
    @FXML
    private Label countOfTasks;
    @FXML
    private Label totalUsedMemory;

    public void loadFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            iTaskService.loadFile(file);
        }

        listOfTasks = iTaskService.getAllFromFile();

        columnName.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        columnPID.setCellValueFactory(new PropertyValueFactory<Task, Long>("PID"));
        columnUsedMemory.setCellValueFactory(new PropertyValueFactory<Task, Long>("usedMemory"));

        tableTasks.setItems(listOfTasks);
        updateCountOfTasks();
        updateTotalUsedMemory();
    }

    public void saveAs(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            iTaskService.saveFile(file);
        }
    }

    public void saveIntoExcel(ActionEvent actionEvent) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Tasks");

        Row mainRow = sheet.createRow(0);
        Cell mainName = mainRow.createCell(0);
        mainName.setCellValue("Name");
        Cell mainPID = mainRow.createCell(1);
        mainPID.setCellValue("PID");
        Cell mainUsedMemory = mainRow.createCell(2);
        mainUsedMemory.setCellValue("Used memory");

        for (int i = 0; i < listOfTasks.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Cell name = row.createCell(0);
            name.setCellValue(listOfTasks.get(i).getName());
            Cell PID = row.createCell(1);
            PID.setCellValue(listOfTasks.get(i).getPID());
            Cell usedMemory = row.createCell(2);
            usedMemory.setCellValue(listOfTasks.get(i).getUsedMemory());
        }

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());

        book.write(new FileOutputStream(file));
        book.close();
    }

    public void start(ActionEvent actionEvent) {
        startCommand();

        listOfTasks = iTaskService.getAllFromConsole();

        columnName.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        columnPID.setCellValueFactory(new PropertyValueFactory<Task, Long>("PID"));
        columnUsedMemory.setCellValueFactory(new PropertyValueFactory<Task, Long>("usedMemory"));

        tableTasks.setItems(listOfTasks);
        updateCountOfTasks();
        updateTotalUsedMemory();
    }

    private void updateCountOfTasks() {
        countOfTasks.setText("Count of tasks: " + listOfTasks.size());
    }

    private void updateTotalUsedMemory() {
        int sum = 0;
        for (Task task : listOfTasks) {
            sum += task.getUsedMemory();
        }

        totalUsedMemory.setText("Total used memory: " + sum);
    }

    private void startCommand() {
        try {
            Runtime runtime = Runtime.getRuntime();
            String[] cmd = {"cmd.exe", "/C", "tasklist"};
            Process proc = runtime.exec(cmd);

            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            String[] parts;

            br.readLine();
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                parts = getValues(line);

                Task task = new Task();
                task.setName(parts[0]);
                task.setPID(Long.parseLong(parts[1]));
                task.setUsedMemory(Long.parseLong(parts[2]));

                iTaskService.addTask(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String[] getValues(String line) {
        String[] values = new String[3];
        String usedMemory;
        String s = "";

        for (int i = 0; i < line.length(); i++) {
            if (Character.toString(line.charAt(i)).equals(" ") && Character.toString(line.charAt(i + 1)).equals(" ")) {
                values[0] = line.substring(0, i);
                String[] parts = getPID(line.substring(i));
                values[1] = parts[0];
                usedMemory = parts[1];
                for (int j = 0; j < usedMemory.length(); j++) {
                    if (intCheck(Character.toString(usedMemory.charAt(j))) != null) {
                        s += Character.toString(usedMemory.charAt(j));
                    }
                }
                break;
            }
        }
        values[2] = s;
        return values;
    }

    private String[] getPID(String line) {
        char[] chars = new char[20];
        String PID = "";
        String usedMemory = "";
        check:
        for (int i = 0, x = 0; i < line.length(); i++) {
            while (Character.toString(line.charAt(i)).equals(" ")) {
                continue check;
            }

            chars[x] = line.charAt(i);
            x++;
            PID = String.valueOf(chars).trim();

            if (Character.toString(line.charAt(i + 1)).equals(" ")) {
                usedMemory = getUsedMemory(line.substring(i + 2).trim());
                break check;
            }
        }
        return new String[]{PID, usedMemory};
    }

    private String getUsedMemory(String line) {
        String usedMemory = "";
        first:
        for (int i = 0; i < line.length(); i++) {
            while (Character.toString(line.charAt(i)).equals(" ")) {
                continue first;
            }
            if (intCheck(Character.toString(line.charAt(i))) == null) {
                continue first;
            } else {
                usedMemory = line.substring(i + 1).trim();
                break first;
            }
        }
        return usedMemory.split(" ")[0];
    }

    private Integer intCheck(String c) {
        try {
            return Integer.parseInt(c);
        } catch (Exception e) {
            return null;
        }
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    MainController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
}
