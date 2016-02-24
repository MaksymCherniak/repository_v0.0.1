package Model.Commands;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Model.Entity.BookAttribute;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatePublishDate implements ICommand {
    private static Logger log = Logger.getLogger(UpdatePublishDate.class.getName());
    private static final String name = "updatepd";
    private String[] parts;
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 3) {
            iBookDAO = new XmlBookDAO();
            if (localDateChecker(parts[2]) != null) {
                iBookDAO.updateBook(BookAttribute.PUBLISH_DATE, parts[1], parts[2]);
            } else {
                log.info("Wrong date format");
                log.info("Date must be like \"YYYY-MM-DD\"");
            }
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- update publish date");
    }

    private LocalDate localDateChecker(String publishDate) {
        String[] partsOfDate = publishDate.split("-");
        try {
            return LocalDate.of(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
