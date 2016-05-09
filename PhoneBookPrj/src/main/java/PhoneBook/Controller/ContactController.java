package PhoneBook.Controller;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.Entity.Contact;
import PhoneBook.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController implements AppStaticValues {
    @Autowired
    private IContactDAO iContactDAO;

    @RequestMapping(value = "/addContact.do", method = RequestMethod.POST)
    public ModelAndView addContact(HttpSession session, @ModelAttribute Contact contact) {
        User user = (User) session.getAttribute(USER);

        contact.setUser(user);
        iContactDAO.insertContact(contact);

        return new ModelAndView(PAGE_USER_MAIN, INFO, contact.toString() + "\nadded");
    }

    @RequestMapping(value = "/deleteContact.do", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam(value = CONTACT_ID) String contact_id) {
        Contact contact = iContactDAO.getContactById(Long.parseLong(contact_id));
        iContactDAO.removeContactById(Long.parseLong(contact_id));

        return new ModelAndView(PAGE_USER_MAIN, INFO, "Contact " + contact.printFullName() + " removed");
    }

    @RequestMapping(value = "/printAllContacts.do", method = RequestMethod.GET)
    public ModelAndView printAllContacts(HttpSession session) {
        User user = (User) session.getAttribute(USER);
        return new ModelAndView(PAGE_PRINT_ALL_CONTACTS, "listOfContacts", iContactDAO.getAllContactsByUserId(user.getId()));
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

        return new ModelAndView(PAGE_USER_MAIN, INFO, "Contact edited.");
    }
}
