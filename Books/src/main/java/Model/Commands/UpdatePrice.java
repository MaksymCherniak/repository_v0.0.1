package Model.Commands;

import DAO.IBookDAO;
import DAO.XmlBookDAO;
import Model.Entity.BookAttribute;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatePrice implements ICommand {
    private static Logger log = Logger.getLogger(UpdatePrice.class.getName());
    private static final String name = "updatep";
    private String[] parts;
    private IBookDAO iBookDAO;

    public void execute(String fullLine) {
        parts = fullLine.split(" ");
        if (parts.length == 3) {
            if (priceCheck(parts[2]) != null) {
                iBookDAO = new XmlBookDAO();
                iBookDAO.updateBook(BookAttribute.PRICE, parts[1], parts[2]);
            } else {
                log.info("Wrong price format or wrong command");
                log.info("Price must be like \"00.00\"");
            }
        } else {
            log.info("Wrong command");
        }
    }

    public void printHelp() {
        System.out.println("- " + name + " -- update price");
    }

    public Double priceCheck(String price) {
        try {
            return Double.parseDouble(price);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }
}
