package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Max on 18.11.2015.
 */
public class DeleteFile implements ICommand {
    private String name = "del";
    private boolean delete;
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- delete file or dir");
    }

    public File execute(String args, File currentDirectory) {
        for (String s : currentDirectory.list())
        {
            if (s.equals(args))
            {
                delete = true;
                File delDir = new File(currentDirectory, args);
                if (!delDir.isDirectory()) {
                    delDir.delete();
                    System.out.println("Delete \"" + delDir.getPath() + "\" ");
                    System.out.println();
                }else{
                    String s1 = "";
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("\"" + args + "\" является каталогом. Вы действительно хотите его удалить?");
                    System.out.println("1. Да");
                    System.out.println("2. Нет");
                    try {
                        s1 = reader.readLine();
                    } catch (IOException e) {}
                    if (s1.equals("1") || s1.equals("Да"))
                    {
                        delDir.delete();
                        System.out.println("Delete \"" + delDir.getPath() + "\" ");
                        System.out.println();
                    }else if (s1.equals("2") || s1.equals("Нет"))
                    {
                        System.out.println("Каталог \"" + args + "\"  не удален.");
                    }
                }
            }
        }
        if (!delete) {
            System.out.println("File \"" + args + "\" not found!");
        }
        return currentDirectory;
    }
}
