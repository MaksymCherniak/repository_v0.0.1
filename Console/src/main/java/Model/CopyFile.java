package Model;

import java.io.*;

/**
 * Created by Max on 18.11.2015.
 */
public class CopyFile implements Command{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String name = "copy";
    private File copyFile;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- copy file");
    }
    public void execute() {

    }

    public File execute(String args, File currentDirectory) {
        copyFile = new File(currentDirectory, args);
        String s2 = "";
        String s = copyFile.getPath();
        try {
            fileInputStream = new FileInputStream(s);
        } catch (FileNotFoundException e) {
        }
        System.out.println("Введите имя файла в который нужно скопировать данные ");
        try {
            copyFile = new File(currentDirectory, reader.readLine());
            s2 = copyFile.getPath();
        } catch (IOException e) {}
        try {
            fileOutputStream = new FileOutputStream(s2);
            while (fileInputStream.available() > 0)
            {
                int data = fileInputStream.read();
                fileOutputStream.write(data);
            }
            System.out.println("Файл \"" + s + "\" скопирован в \"" + s2 + "\"");
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return currentDirectory;
    }

    public File execute(File currentDirectory) {
        return null;
    }
}

