package searcher.entity;

import org.apache.log4j.Logger;

import java.io.File;

public class Folder {
    private static Logger log = Logger.getLogger(Folder.class.getName());

    private final static File DEFAULT_START_FOLDER = new File("C://");
    private final static File DEFAULT_PROCESSED_FILES_FOLDER = new File("C://ProcessedFiles");
    private final static File DEFAULT_FAILED_FOLDER = new File("C://FailedFiles");
    private final static long DEFAULT_MONITORING_PERIOD = 600000;

    private static File startFolder = DEFAULT_START_FOLDER;
    private static File processedFilesFolder = DEFAULT_PROCESSED_FILES_FOLDER;
    private static File failedFolder = DEFAULT_FAILED_FOLDER;
    private static long monitoringPeriod = DEFAULT_MONITORING_PERIOD;

    public Folder() {
    }

    public static File getStartFolder() {
        return startFolder;
    }

    public static void setStartFolder(String url) {
        setFolder(url, "start");
    }

    public static File getProcessedFilesFolder() {
        return processedFilesFolder;
    }

    public static void setProcessedFilesFolder(String url) {
        setFolder(url, "processed files");
    }

    public static File getFailedFolder() {
        return failedFolder;
    }

    public static void setFailedFolder(String url) {
        setFolder(url, "failed");
    }

    public static long getMonitoringPeriod() {
        return monitoringPeriod;
    }

    public static void setMonitoringPeriod(String monitoringPeriod) {
        if (checkMP(monitoringPeriod) != null) {
            log.info("Success. Current monitoring period is " + monitoringPeriod);
            Folder.monitoringPeriod = Long.parseLong(monitoringPeriod);
        } else {
            log.warn("Failed. Wrong format for monitoring period");
        }
    }

    public static void setDefaultStartFolder() {
        startFolder = DEFAULT_START_FOLDER;
        log.info("Success. Current start folder is " + startFolder.getAbsolutePath());
    }

    public static void setDefaultProcessedFilesFolder() {
        processedFilesFolder = DEFAULT_PROCESSED_FILES_FOLDER;
        log.info("Success. Current processed files folder is " + processedFilesFolder.getAbsolutePath());
    }

    public static void setDefaultFailedFolder() {
        failedFolder = DEFAULT_FAILED_FOLDER;
        log.info("Success. Current start folder is " + failedFolder.getAbsolutePath());
    }

    public static void setDefaultMonitoringPeriod() {
        monitoringPeriod = DEFAULT_MONITORING_PERIOD;
        log.info("Success. Current monitoring period is " + monitoringPeriod);
    }

    private static Long checkMP(String monitoringPeriod) {
        try {
            return Long.parseLong(monitoringPeriod);
        } catch (Exception e) {
            return null;
        }
    }

    private static void setFolder(String url, String folder) {
        File file = new File(url);
        if (file.exists() && file.isDirectory()) {
            log.info("Success. Current " + folder + " folder is " + url);
            switch (folder) {
                case "start":
                    startFolder = file;
                    break;
                case "processed files":
                    processedFilesFolder = file;
                    break;
                case "failed":
                    failedFolder = file;
                    break;
            }
        } else {
            log.warn("Failed. " + url + " does not exist or it is file");
        }
    }
}
