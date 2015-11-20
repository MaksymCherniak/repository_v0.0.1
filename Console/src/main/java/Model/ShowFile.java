package Model;

import java.io.*;

/**
 * Created by Max on 18.11.2015.
 */
public class ShowFile implements ICommand {
    private String name = "type";
    private File type;
    private FileInputStream inputStream;
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- show file ( content )");
    }

    public File execute(String args, File currentDirectory) {
        type = new File(currentDirectory, args);
        try {
            inputStream = new FileInputStream(type.getPath());
        } catch (FileNotFoundException e) {}
        System.out.println("Информация о файле \"" + args + "\":");
        System.out.println();
        System.out.println("Имя файла: " + type.getName());
        System.out.println("Путь к файлу: " + type.getPath());
        System.out.println(type.canRead() ? "Файл доступен для чтения. " : "Файл не доступен для чтения.");
        System.out.println(type.canWrite() ? "Файл доступен для записи. " : "Файл не доступен для записи. ");
        System.out.println(type.isDirectory() ? "Является каталогом. " : "Не является каталогом. ");
        System.out.println("Последнее изменение в файле: " + type.lastModified());
        System.out.println("Размер файла: " + type.length() + " байт.");
        System.out.println();
        System.out.println("Содержимое файла: ");
        System.out.println();
        loadContent(inputStream);
        System.out.println();
        return currentDirectory;
    }
    public void loadContent(InputStream inputStream)
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready())
            {
                String s = reader.readLine();
                System.out.println(s);
            }
        }catch (IOException e)
        {}
    }
}
