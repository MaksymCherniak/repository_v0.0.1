package PhoneBook.Controller;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.Contact;
import PhoneBook.Entity.User;
import PhoneBook.Entity.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private IContactDAO iContactDAO;
    @Autowired
    private IUserDAO iUserDAO;
    private static final String INFO_VALUE = "info";
    private static final String EDIT_PAGE = "editContact";
    private static final String ADD_PAGE = "addContact";
    private static final String USER_PAGE = "userMainPage";
    private static final String PRINT_ALL_PAGE = "printAllContacts";
    private static final String USER = "user";
    private static final String CONTACT = "contact";
    private static final String USER_ID = "user_id";
    private static final String CONTACT_ID = "contact_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYM = "patronym";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String MOBILE = "mobile";
    private static final String HOME = "home";

    @RequestMapping(value = "/addContact.do", method = RequestMethod.GET)
    public ModelAndView addContact(@RequestParam(value = USER_ID) String user_id) {
        User user = iUserDAO.findUserById(Long.parseLong(user_id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_PAGE);
        modelAndView.addObject(USER, user);
        return modelAndView;
    }

    @RequestMapping(value = "/addContact.do", method = RequestMethod.POST)
    public ModelAndView addContact(@RequestParam(value = USER_ID) String user_id,
                                   @RequestParam(value = SURNAME) String surname,
                                   @RequestParam(value = NAME) String name,
                                   @RequestParam(value = PATRONYM) String patronym,
                                   @RequestParam(value = MOBILE) String mobilePhone,
                                   @RequestParam(value = HOME) String homePhone,
                                   @RequestParam(value = ADDRESS) String address,
                                   @RequestParam(value = EMAIL) String email) {

        ModelAndView modelAndView = new ModelAndView();
        User user = iUserDAO.findUserById(Long.parseLong(user_id));

        if (!Validator.surname(surname)) {
            modelAndView.setViewName(ADD_PAGE);
            modelAndView.addObject(INFO_VALUE, "Wrong surname format.");
            modelAndView.addObject(USER, user);
            return modelAndView;
        }
        if (!Validator.name(name)) {
            modelAndView.setViewName(ADD_PAGE);
            modelAndView.addObject(INFO_VALUE, "Wrong name format.");
            modelAndView.addObject(USER, user);
            return modelAndView;
        }
        if (!Validator.patronym(patronym)) {
            modelAndView.setViewName(ADD_PAGE);
            modelAndView.addObject(INFO_VALUE, "Wrong patronym format.");
            modelAndView.addObject(USER, user);
            return modelAndView;
        }
        if (!Validator.email(email)) {
            modelAndView.setViewName(ADD_PAGE);
            modelAndView.addObject(INFO_VALUE, "Wrong email format.");
            modelAndView.addObject(USER, user);
            return modelAndView;
        }
        Contact contact = new Contact(surname, name, patronym, email, address, mobilePhone, homePhone, user);
        iContactDAO.insertContact(contact);
        modelAndView.setViewName(USER_PAGE);
        modelAndView.addObject(INFO_VALUE, contact.toString() + "\nadded");
        modelAndView.addObject(USER, user);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteContact.do", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam(value = CONTACT_ID) String contact_id) {
        ModelAndView modelAndView = new ModelAndView();
        Contact contact = iContactDAO.getContactById(Long.parseLong(contact_id));
        User user = iUserDAO.findUserById(contact.getUser().getId());
        iContactDAO.removeContactById(Long.parseLong(contact_id));
        modelAndView.setViewName(USER_PAGE);
        modelAndView.addObject(USER, user);
        modelAndView.addObject(INFO_VALUE, "Contact " + contact.printFullName() + " removed");
        return modelAndView;
    }

    @RequestMapping(value = "/printAllContacts.do", method = RequestMethod.GET)
    public ModelAndView printAllContacts(@RequestParam(value = USER_ID) String user_id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Contact> listOfContacts = iContactDAO.getAllContactsByUserId(Long.parseLong(user_id));

        modelAndView.setViewName(PRINT_ALL_PAGE);
        modelAndView.addObject("listOfContacts", listOfContacts);

        return modelAndView;
    }

    @RequestMapping(value = "/editContact.do", method = RequestMethod.GET)
    public ModelAndView editContact(@RequestParam(value = CONTACT_ID) String contact_id) {
        ModelAndView modelAndView = new ModelAndView();
        Contact contact = iContactDAO.getContactById(Long.parseLong(contact_id));

        modelAndView.setViewName(EDIT_PAGE);
        modelAndView.addObject(CONTACT, contact);

        return modelAndView;
    }

    @RequestMapping(value = "/editContact.do", method = RequestMethod.POST)
    public ModelAndView editContact(@RequestParam(value = CONTACT_ID) String contact_id,
                                    @RequestParam(value = NAME) String name,
                                    @RequestParam(value = SURNAME) String surname,
                                    @RequestParam(value = PATRONYM) String patronym,
                                    @RequestParam(value = EMAIL) String email,
                                    @RequestParam(value = ADDRESS) String address,
                                    @RequestParam(value = MOBILE) String mobile,
                                    @RequestParam(value = HOME) String home) {

        ModelAndView modelAndView = new ModelAndView();
        Contact contact = iContactDAO.getContactById(Long.parseLong(contact_id));

        if (!name.equals("") && !Validator.name(name)) {
            modelAndView.addObject(CONTACT, contact);
            modelAndView.addObject(INFO_VALUE, "Wrong name format.");
            modelAndView.setViewName(EDIT_PAGE);
            return modelAndView;
        }
        if (!surname.equals("") && !Validator.surname(surname)) {
            modelAndView.addObject(CONTACT, contact);
            modelAndView.addObject(INFO_VALUE, "Wrong surname format.");
            modelAndView.setViewName(EDIT_PAGE);
            return modelAndView;
        }
        if (!patronym.equals("") && !Validator.patronym(patronym)) {
            modelAndView.addObject(CONTACT, contact);
            modelAndView.addObject(INFO_VALUE, "Wrong patronym format.");
            modelAndView.setViewName(EDIT_PAGE);
            return modelAndView;
        }
        if (!email.equals("") && !Validator.email(email)) {
            modelAndView.addObject(CONTACT, contact);
            modelAndView.addObject(INFO_VALUE, "Wrong email format.");
            modelAndView.setViewName(EDIT_PAGE);
            return modelAndView;
        }
        if (!name.equals("")) {
            contact.setName(name);
        }
        if (!surname.equals("")) {
            contact.setSurname(surname);
        }
        if (!patronym.equals("")) {
            contact.setPatronym(patronym);
        }
        if (!email.equals("")) {
            contact.setEmail(email);
        }
        if (!address.equals("")) {
            contact.setAddress(address);
        }
        if (!mobile.equals("")) {
            contact.setMobilePhoneNumber(mobile);
        }
        if (!home.equals("")) {
            contact.setHomePhoneNumber(home);
        }
        iContactDAO.insertContact(contact);
        modelAndView.setViewName(USER_PAGE);
        modelAndView.addObject(USER, contact.getUser());
        modelAndView.addObject(INFO_VALUE, "Contact edited.");

        return modelAndView;
    }
}
