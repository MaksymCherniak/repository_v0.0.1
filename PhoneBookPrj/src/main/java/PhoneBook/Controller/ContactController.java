package PhoneBook.Controller;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.Contact;
import PhoneBook.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController implements AppStaticValues {
    @Autowired
    private IContactDAO iContactDAO;
    @Autowired
    private IUserDAO iUserDAO;

    @RequestMapping(value = "/addContactGet.do", method = RequestMethod.GET)
    public ModelAndView addContact(@RequestParam(value = USER_ID) String user_id) {
        return new ModelAndView(PAGE_ADD_CONTACT, USER, iUserDAO.findUserById(Long.parseLong(user_id)));
    }

    @RequestMapping(value = "/addContact.do", method = RequestMethod.POST)
    public ModelAndView addContact(@RequestParam(value = USER_ID) String user_id, @ModelAttribute Contact contact) {
        User user = iUserDAO.findUserById(Long.parseLong(user_id));

        contact.setUser(user);
        iContactDAO.insertContact(contact);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_USER_MAIN);
        modelAndView.addObject(INFO, contact.toString() + "\nadded");
        modelAndView.addObject(USER, user);

        return modelAndView;
    }

    @RequestMapping(value = "/deleteContact.do", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam(value = CONTACT_ID) String contact_id) {
        Contact contact = iContactDAO.getContactById(Long.parseLong(contact_id));
        iContactDAO.removeContactById(Long.parseLong(contact_id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_USER_MAIN);
        modelAndView.addObject(USER, iUserDAO.findUserById(contact.getUser().getId()));
        modelAndView.addObject(INFO, "Contact " + contact.printFullName() + " removed");

        return modelAndView;
    }

    @RequestMapping(value = "/printAllContacts.do", method = RequestMethod.GET)
    public ModelAndView printAllContacts(@RequestParam(value = USER_ID) String user_id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_PRINT_ALL_CONTACTS);
        modelAndView.addObject(USER, iUserDAO.findUserById(Long.parseLong(user_id)));
        modelAndView.addObject("listOfContacts", iContactDAO.getAllContactsByUserId(Long.parseLong(user_id)));

        return modelAndView;
    }

    @RequestMapping(value = "/editContactGet.do", method = RequestMethod.GET)
    public ModelAndView editContact(@RequestParam(value = CONTACT_ID) String contact_id) {
        return new ModelAndView(PAGE_EDIT_CONTACT, CONTACT, iContactDAO.getContactById(Long.parseLong(contact_id)));
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

        Contact contact = iContactDAO.getContactById(Long.parseLong(contact_id));

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
            contact.setMobile(mobile);
        }
        if (!home.equals("")) {
            contact.setHome(home);
        }
        iContactDAO.updateContact(contact);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PAGE_USER_MAIN);
        modelAndView.addObject(USER, contact.getUser());
        modelAndView.addObject(INFO, "Contact edited.");

        return modelAndView;
    }
}
