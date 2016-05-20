package BookingTool.Entity;

import java.util.logging.Logger;

public class Validator {
    private static Logger log = Logger.getLogger(Validator.class.getName());
    private static String[] parts;
    private static String[] emailDom = {"bigmir.net", "ukr.net", "mail.ua", "gmail.com", "mail.ru", "yandex.ru", "rambler.ru"
            , "qip.ru", "hotmail.com", "live.com", "msn.com", "yahoo.com", "ymail.com", "rocketmail.com", "aol.com", "inbox.ru"
            , "list.ru", "bk.ru"};

    public static boolean password(String password) {
        if (password.toCharArray().length > 4) {
            return true;
        }
        return false;
    }

    public static boolean surname(String surname) {
        if (surname.toCharArray().length > 3) {
            return true;
        }
        return false;
    }

    public static boolean name(String name) {
        if (name.toCharArray().length > 3) {
            return true;
        }
        return false;
    }

    public static boolean email(String email) {
        if (!email.contains("@")) {
            return false;
        }
        parts = email.split("@");
        for (int i = 0; i < emailDom.length; i++) {
            if (emailDom[i].equals(parts[1])) {
                return true;
            }
        }
        return false;
    }
}
