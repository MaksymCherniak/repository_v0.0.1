package Model;

import java.io.*;

/**
 * Created by Max on 18.11.2015.
 */
public class CompareContentOfFiles implements Command {
    private String name = "fc";
    private String[] parts;
    private FileInputStream firstFile;
    private FileInputStream secondFile;
    private File currentFirstDir;
    private File currentSecondDir;
    public String getName() {
        return name;
    }

    public void printHelp() {
        System.out.println("-" + name + " -- compare content of files");
    }
    public void execute() {
    }
    public File execute(String args, File currentDirectory) {
        parts = args.split(" ");
        currentFirstDir = new File(currentDirectory, parts[0]);
        currentSecondDir = new File(currentDirectory, parts[1]);
        try {
            firstFile = new FileInputStream(currentFirstDir.getPath());
            secondFile = new FileInputStream(currentSecondDir.getPath());
        } catch (FileNotFoundException e) {}

        if (compare(firstFile, secondFile))
            System.out.println("Содержимое файлов \"" + currentFirstDir.getPath() + "\" и \"" + currentSecondDir.getPath() + "\" идентично. ");
        else System.out.println("Содержимое файлов \"" + currentFirstDir.getPath() + "\" и \"" + currentSecondDir.getPath() + "\" не идентично. ");
        return currentDirectory;
    }
    public boolean compare(InputStream firstFile, InputStream secondFile)
    {
        boolean compare = true;
        BufferedReader firstReader = new BufferedReader(new InputStreamReader(firstFile));
        BufferedReader secondReader = new BufferedReader(new InputStreamReader(secondFile));
        try {
            while (firstReader.ready() || secondReader.ready())
            {
                String s1 = firstReader.readLine();
                String s2 = secondReader.readLine();
                if (s1.equals(s2))
                    compare = true;
                else compare = false;
            }
        } catch (IOException e) {}
        return compare;
    }
    public File execute(File currentDirectory) {
        return null;
    }
}
