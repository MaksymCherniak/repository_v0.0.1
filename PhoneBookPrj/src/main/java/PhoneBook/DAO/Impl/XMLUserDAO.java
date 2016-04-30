package PhoneBook.DAO.Impl;

import PhoneBook.DAO.Service.IUserDAO;
import PhoneBook.Entity.User;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLUserDAO implements IUserDAO {
    private static Logger log = Logger.getLogger(XMLUserDAO.class.getName());
    @Autowired
    private File fileXmlUser;
    @Autowired
    private DocumentBuilder documentBuilder;
    private Document doc;
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FULL_NAME = "fullName";

    public XMLUserDAO(){
        if (!fileXmlUser.exists()){
            createXmlFile();
        }
    }

    public boolean insertUser(User user) {
        if (findUserById(user.getId()) != null) {
            return false;
        }
        try {
            doc = documentBuilder.parse(fileXmlUser);
            Node rootNode = doc.getFirstChild();

            Element userElement = doc.createElement("user");
            rootNode.appendChild(userElement);
            userElement.setAttribute(ID, String.valueOf(user.getId()));

            Element loginElement = doc.createElement(LOGIN);
            loginElement.appendChild(doc.createTextNode(user.getLogin()));
            userElement.appendChild(loginElement);

            Element passwordElement = doc.createElement(PASSWORD);
            passwordElement.appendChild(doc.createTextNode(user.getPassword()));
            userElement.appendChild(passwordElement);

            Element fullNameElement = doc.createElement(FULL_NAME);
            fullNameElement.appendChild(doc.createTextNode(user.getFullName()));
            userElement.appendChild(fullNameElement);

            initTransformer();
            log.info(user.toString() + "\nUser added.");
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public User findUserByLoginAndPassword(String login, String password) {
        try {
            doc = documentBuilder.parse(fileXmlUser);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfUsers.getLength(); i++) {
                Node node = listOfUsers.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String xmlLogin = element.getElementsByTagName(LOGIN).item(0).getChildNodes().item(0).getNodeValue();
                    String xmlPassword = element.getElementsByTagName(PASSWORD).item(0).getChildNodes().item(0).getNodeValue();
                    if (xmlLogin.equals(login) && xmlPassword.equals(password)) {
                        User user = new User();
                        user.setId(Long.parseLong(element.getAttribute(ID)));
                        user.setLogin(xmlLogin);
                        user.setPassword(xmlPassword);
                        user.setFullName(element.getElementsByTagName(FULL_NAME).item(0).getChildNodes().item(0).getNodeValue());
                        return user;
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("User not found");
        return null;
    }

    public User findUserById(long id) {
        try {
            doc = documentBuilder.parse(fileXmlUser);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfUsers.getLength(); i++) {
                Node node = listOfUsers.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(ID).equals(String.valueOf(id))) {
                        User user = new User();
                        user.setId(Long.parseLong(element.getAttribute(ID)));
                        user.setLogin(element.getElementsByTagName(LOGIN).item(0).getChildNodes().item(0).getNodeValue());
                        user.setPassword(element.getElementsByTagName(PASSWORD).item(0).getChildNodes().item(0).getNodeValue());
                        user.setFullName(element.getElementsByTagName(FULL_NAME).item(0).getChildNodes().item(0).getNodeValue());
                        return user;
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("User not found");
        return null;
    }

    private void initTransformer() {
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(fileXmlUser);
            t.transform(new DOMSource(doc), result);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }

    private void createXmlFile() {
        try {
            doc = documentBuilder.newDocument();

            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);

            initTransformer();
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }
}
