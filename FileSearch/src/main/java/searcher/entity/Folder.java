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
        File file = new File(url);
        if (file.exists()) {
            log.info("Success. Current start folder is " + url);
            startFolder = file;
        } else {
            log.warn("Failed. " + url + " does not exist");
        }
    }

    public static File getProcessedFilesFolder() {
        return processedFilesFolder;
    }

    public static void setProcessedFilesFolder(String url) {
        File file = new File(url);
        if (file.exists()) {
            log.info("Success. Current processed files folder is " + url);
            processedFilesFolder = file;
        } else {
            log.warn("Failed. " + url + " does not exist");
        }
    }

    public static File getFailedFolder() {
        return failedFolder;
    }

    public static void setFailedFolder(String url) {
        File file = new File(url);
        if (file.exists()) {
            log.info("Success. Current start folder is " + url);
            failedFolder = file;
        } else {
            log.warn("Failed. " + url + " does not exist");
        }
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
}
