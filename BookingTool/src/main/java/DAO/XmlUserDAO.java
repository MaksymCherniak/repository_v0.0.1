package DAO;

import Model.LocalModel.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 08.12.2015.
 */
public class XmlUserDAO implements IUserDAO {
    private final File fileXml = new File("Users.xml");
    private DocumentBuilder builder;
    private Document doc;
    private boolean idChecker = false;
    public XmlUserDAO(){
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {}
        if (!fileXml.exists()){
            createXmlFile();
        }
    }

    public void createXmlFile() {
        try {
            doc = builder.newDocument();

            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);

            initTransformer();
        } catch (Exception e) {}
    }

    public User findUser(String id) {
        User user = new User();
        try{
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for(int i = 0; i < listOfUsers.getLength(); i++)
            {
                Node node = listOfUsers.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    if (element.getAttribute("id").equals(id)){
                        String ticket = element.getAttribute("id");
                        String name = element.getElementsByTagName("firstName").item(0).getChildNodes().item(0).getNodeValue();
                        String surname = element.getElementsByTagName("lastName").item(0).getChildNodes().item(0).getNodeValue();
                        user.setTicket(ticket);
                        user.setFirstName(name);
                        user.setLastName(surname);
                        break;
                    }
                }
            }
            initTransformer();
            return user;
        }catch (Exception e){}
        return null;
    }

    public int insertUser(User user) {
        try{
            doc = builder.parse(fileXml);
            Node rootNode = doc.getFirstChild();

            Element userElement = doc.createElement("User");
            rootNode.appendChild(userElement);
            userElement.setAttribute("id", String.valueOf(user.getIndex()));

            Element lastNameElement = doc.createElement("lastName");
            lastNameElement.appendChild(doc.createTextNode(user.getLastName()));
            userElement.appendChild(lastNameElement);

            Element firstNameElement = doc.createElement("firstName");
            firstNameElement.appendChild(doc.createTextNode(user.getFirstName()));
            userElement.appendChild(firstNameElement);

            initTransformer();
            return user.getIndex();
        }catch (Exception e){
            return -1;
        }
    }

    public void deleteUser(User user) {
        try{
            doc = builder.parse(fileXml);
            Node rootNode = doc.getFirstChild();
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for(int i = 0; i < listOfUsers.getLength(); i++)
            {
                Node node = listOfUsers.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    if (element.getAttribute("id").equals(user.getTicket())){
                        rootNode.removeChild(node);
                    }
                }
            }
            initTransformer();
        }catch (Exception e){}
        System.out.println("User " + user.getTicket() + " removed");
    }

    public List getAllUsers() {
        List<String> listOfUsers = new ArrayList<String>();
        try{
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();
            NodeList usersList = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());

            for (int i = 0; i < usersList.getLength(); i++){
                Node node = usersList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    StringBuilder s1 = new StringBuilder();
                    s1.append("ID: " + element.getAttribute("id")).append(" Full name: ")
                            .append(element.getElementsByTagName("lastName").item(0).getChildNodes().item(0).getNodeValue())
                            .append(" " + element.getElementsByTagName("firstName").item(0).getChildNodes()
                                    .item(0).getNodeValue());
                    listOfUsers.add(String.valueOf(s1));
                }
            }
        }catch (Exception e){}
        return listOfUsers;
    }

    public void setIndex(){
        try {
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            Node lastUser = listOfUsers.item(listOfUsers.getLength() - 1);
            if(lastUser.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) lastUser;
                User.setIndex(element.getAttribute("id"));
            }
        }catch (Exception e){}
    }

    public boolean checkId(String id){
        try{
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfUsers = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for(int i = 0; i < listOfUsers.getLength(); i++)
            {
                Node node = listOfUsers.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    if (element.getAttribute("id").equals(id)){
                        idChecker = true;
                        return idChecker;
                    }
                }
            }
        }catch (Exception e){}
        return idChecker;
    }

    private void initTransformer(){
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(fileXml));
        }catch (Exception e){}
    }
}
