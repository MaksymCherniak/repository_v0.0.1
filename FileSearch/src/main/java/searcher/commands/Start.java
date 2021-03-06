package searcher.commands;

import org.apache.log4j.Logger;
import searcher.dao.service.IContentService;
import searcher.entity.Folder;
import searcher.mainClasses.ApplicationContext;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start extends Thread implements ICommand {
    private static Logger log = Logger.getLogger(Start.class.getName());
    private IContentService iContentService = ApplicationContext.getCtx().getBean("IContentService", IContentService.class);
    private static ExecutorService service;
    private static final int DEFAULT_QUANTITY_THREADS = 50;
    private File currentDirectory;
    private final static String name = "start";
    private String[] parts;

    public Start() {
    }

    public Start(ExecutorService service, File currentDirectory) {
        this.service = service;
        this.currentDirectory = currentDirectory;
    }

    public void execute(String fullLine) {
        parts = fullLine.split(SPACE);

        if (parts.length != 1) {
            log.info(WRONG_COMMAND);
            return;
        }

        service = Executors.newFixedThreadPool(DEFAULT_QUANTITY_THREADS);

        this.currentDirectory = Folder.getStartFolder();

        service.execute(this);

        Thread periodThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Folder.getMonitoringPeriod());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Start().execute("start");
            }
        };

        service.execute(periodThread);
    }

    public String printHelp() {
        return "- " + name + " -- start monitoring.";
    }

    @Override
    public void run() {
        if (currentDirectory.isDirectory()) {
            if (currentDirectory.canRead()) {
                for (File temp : currentDirectory.listFiles()) {
                    if (temp.isDirectory()) {
                        service.execute(new Start(service, temp));
                    } else if (temp.getName().contains(".xml")) {
                        operationsWithFile(temp);
                    }
                }
            }
        } else {
            if (currentDirectory.getName().contains(".xml")) {
                operationsWithFile(currentDirectory);
            }
        }
    }

    private void operationsWithFile(File file) {
        if (iContentService.addContent(file)) {
            file.renameTo(new File(Folder.getProcessedFilesFolder(), file.getName()));
        } else {
            file.renameTo(new File(Folder.getFailedFolder(), file.getName()));
        }
    }
}
