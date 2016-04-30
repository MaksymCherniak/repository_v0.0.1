package PhoneBook.DAO.Impl;

import PhoneBook.DAO.Service.IContactDAO;
import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLContactDAO implements IContactDAO {
    private static Logger log = Logger.getLogger(XMLContactDAO.class.getName());
    @Autowired
    private IUserDAO iUserDAO;
    @Autowired
    private File fileXmlContact;
    @Autowired
    private DocumentBuilder documentBuilder;
    private Document doc;
    private static final String USER_ID = "user_id";
    private static final String CONTACT_ID = "contact_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYM = "patronym";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String MOBILE = "mobile";
    private static final String HOME = "home";

    public XMLContactDAO(){
        if (!fileXmlContact.exists()){
            createXmlFile();
        }
    }

    public boolean insertContact(Contact contact) {
        if (getContactById(contact.getId()) != null) {
            return false;
        }
        try {
            doc = documentBuilder.parse(fileXmlContact);
            Node rootNode = doc.getFirstChild();

            Element contactElement = doc.createElement("contact");
            rootNode.appendChild(contactElement);
            contactElement.setAttribute(CONTACT_ID, String.valueOf(contact.getId()));
            contactElement.setAttribute(USER_ID, String.valueOf(contact.getUser().getId()));

            Element surnameElement = doc.createElement(SURNAME);
            surnameElement.appendChild(doc.createTextNode(contact.getSurname()));
            contactElement.appendChild(surnameElement);

            Element nameElement = doc.createElement(NAME);
            nameElement.appendChild(doc.createTextNode(contact.getName()));
            contactElement.appendChild(nameElement);

            Element patronymElement = doc.createElement(PATRONYM);
            patronymElement.appendChild(doc.createTextNode(contact.getPatronym()));
            contactElement.appendChild(patronymElement);

            Element emailElement = doc.createElement(EMAIL);
            emailElement.appendChild(doc.createTextNode(contact.getEmail()));
            contactElement.appendChild(emailElement);

            Element addressElement = doc.createElement(PATRONYM);
            addressElement.appendChild(doc.createTextNode(contact.getAddress()));
            contactElement.appendChild(addressElement);

            Element mobileElement = doc.createElement(MOBILE);
            mobileElement.appendChild(doc.createTextNode(contact.getMobilePhoneNumber()));
            contactElement.appendChild(mobileElement);

            Element homeElement = doc.createElement(PATRONYM);
            homeElement.appendChild(doc.createTextNode(contact.getHomePhoneNumber()));
            contactElement.appendChild(homeElement);

            initTransformer();
            log.info(contact.toString() + "\nContact added.");
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public List<Contact> getAllContactsByUserId(long user_id) {
        try {
            List<Contact> listOFContacts = new ArrayList<Contact>();
            doc = documentBuilder.parse(fileXmlContact);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfUsers.getLength(); i++) {
                Node node = listOfUsers.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(USER_ID).equals(String.valueOf(user_id))) {
                        listOFContacts.add(getContactByElement(element));
                    }
                }
            }
            return listOFContacts;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("Contact not found");
        return null;
    }

    public void removeContactById(long contact_id) {
        try{
            doc = documentBuilder.parse(fileXmlContact);
            Node rootNode = doc.getFirstChild();
            doc.getDocumentElement().normalize();

            NodeList listOfContacts = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for(int i = 0; i < listOfContacts.getLength(); i++)
            {
                Node node = listOfContacts.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    if (element.getAttribute(CONTACT_ID).equals(String.valueOf(contact_id))){
                        rootNode.removeChild(node);
                    }
                }
            }
            initTransformer();
            log.info("Contact " + contact_id + " removed");
            return;
        }catch (Exception e){
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("Contact " + contact_id + " didn't remove");
    }

    public Contact getContactById(long contact_id) {
        try {
            doc = documentBuilder.parse(fileXmlContact);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfUsers.getLength(); i++) {
                Node node = listOfUsers.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(CONTACT_ID).equals(String.valueOf(contact_id))) {
                        return getContactByElement(element);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("Contact not found");
        return null;
    }

    public void updateContact(Contact contact) {
        try {
            doc = documentBuilder.parse(fileXmlContact);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfUsers.getLength(); i++) {
                Node node = listOfUsers.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(CONTACT_ID).equals(String.valueOf(contact.getId()))) {
                        Contact xmlContact = getContactByElement(element);
                        if (xmlContact.equals(contact)) {
                            log.info("Contact didn't edit");
                            return;
                        }
                        if (!xmlContact.getSurname().equals(contact.getSurname())) {
                            element.getElementsByTagName(SURNAME).item(0).getChildNodes().item(0).setNodeValue(contact.getSurname());
                        }
                        if (!xmlContact.getName().equals(contact.getName())) {
                            element.getElementsByTagName(NAME).item(0).getChildNodes().item(0).setNodeValue(contact.getName());
                        }
                        if (!xmlContact.getPatronym().equals(contact.getPatronym())) {
                            element.getElementsByTagName(PATRONYM).item(0).getChildNodes().item(0).setNodeValue(contact.getPatronym());
                        }
                        if (!xmlContact.getEmail().equals(contact.getEmail())) {
                            element.getElementsByTagName(EMAIL).item(0).getChildNodes().item(0).setNodeValue(contact.getEmail());
                        }
                        if (!xmlContact.getAddress().equals(contact.getAddress())) {
                            element.getElementsByTagName(ADDRESS).item(0).getChildNodes().item(0).setNodeValue(contact.getAddress());
                        }
                        if (!xmlContact.getMobilePhoneNumber().equals(contact.getMobilePhoneNumber())) {
                            element.getElementsByTagName(MOBILE).item(0).getChildNodes().item(0).setNodeValue(contact.getMobilePhoneNumber());
                        }
                        if (!xmlContact.getHomePhoneNumber().equals(contact.getHomePhoneNumber())) {
                            element.getElementsByTagName(HOME).item(0).getChildNodes().item(0).setNodeValue(contact.getHomePhoneNumber());
                        }
                        log.info("Contact edited");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("Contact not found");
    }

    private Contact getContactByElement (Element element) {
        Contact contact = new Contact();
        contact.setId(Long.parseLong(element.getAttribute(CONTACT_ID)));
        contact.setSurname(element.getElementsByTagName(SURNAME).item(0).getChildNodes().item(0).getNodeValue());
        contact.setName(element.getElementsByTagName(NAME).item(0).getChildNodes().item(0).getNodeValue());
        contact.setPatronym(element.getElementsByTagName(PATRONYM).item(0).getChildNodes().item(0).getNodeValue());
        contact.setEmail(element.getElementsByTagName(EMAIL).item(0).getChildNodes().item(0).getNodeValue());
        contact.setAddress(element.getElementsByTagName(ADDRESS).item(0).getChildNodes().item(0).getNodeValue());
        contact.setMobilePhoneNumber(element.getElementsByTagName(MOBILE).item(0).getChildNodes().item(0).getNodeValue());
        contact.setHomePhoneNumber(element.getElementsByTagName(HOME).item(0).getChildNodes().item(0).getNodeValue());
        contact.setUser(iUserDAO.findUserById(Long.parseLong(element.getElementsByTagName(USER_ID)
                .item(0).getChildNodes().item(0).getNodeValue())));
        return contact;
    }

    private void initTransformer() {
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(fileXmlContact));
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }

    private void createXmlFile() {
        try {
            doc = documentBuilder.newDocument();

            Element rootElement = doc.createElement("Contacts");
            doc.appendChild(rootElement);

            initTransformer();
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }
}
