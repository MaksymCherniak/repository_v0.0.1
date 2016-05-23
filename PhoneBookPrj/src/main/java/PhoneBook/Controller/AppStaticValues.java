package PhoneBook.Controller;

public interface AppStaticValues {

    String LANG = "lang";

    // ------------------------ Contact --------------------------

    String NAME = "name";
    String SURNAME = "surname";
    String PATRONYM = "patronym";
    String EMAIL = "email";
    String ADDRESS = "address";
    String MOBILE = "mobile";
    String HOME = "home";
    String CONTACT = "contact";
    String USER_ID = "user_id";
    String CONTACT_ID = "contact_id";

    // -------------------------- User ---------------------------

    String LOGIN = "login";
    String PASSWORD = "password";
    String FULL_NAME = "fullName";
    String INFO = "info";
    String USER = "user";

    // ------------------ Pages for Controller -------------------

    String PAGE_PRINT_ALL_CONTACTS = "printAllContacts";
    String PAGE_EDIT_CONTACT = "editContact";
    String PAGE_MAIN = "main";
    String PAGE_USER_MAIN = "userMainPage";
    String PAGE_ADD_CONTACT = "addContact";
    String PAGE_REGISTRATION = "registerPage";

    // ----------------- Pages for Interceptors ------------------

    String JSP_PAGE_EDIT_CONTACT = "/WEB-INF/pages/editContact.jsp";
    String JSP_PAGE_MAIN = "/WEB-INF/pages/main.jsp";
    String JSP_REGISTER_PAGE = "/WEB-INF/pages/registerPage.jsp";
    String JSP_PAGE_ADD_CONTACT = "/WEB-INF/pages/addContact.jsp";
}
