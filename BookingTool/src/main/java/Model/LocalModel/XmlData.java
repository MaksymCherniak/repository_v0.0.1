package Model.LocalModel;

import DAO.IXmlDAO;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * Created by Max on 04.12.2015.
 */
public class XmlData implements IXmlDAO {
    private final File fileXml = new File("Users.xml");
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private boolean idChecker = false;
    public XmlData(){
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {}
        if (!fileXml.exists()){
            create();
        }
    }
    public void create() {
        try {
            doc = builder.newDocument();

            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileXml.getName())));
        } catch (Exception e) {}
    }

    public void read(){
        try{
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();
            NodeList usersList = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());

            for (int i = 0; i < usersList.getLength(); i++){
                Node node = usersList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    System.out.print("ID: " + element.getAttribute("id") + " Full name: " + element.getElementsByTagName("lastName")
                            .item(0).getChildNodes().item(0).getNodeValue());
                    System.out.print(" ");
                    System.out.print(element.getElementsByTagName("firstName").item(0).getChildNodes()
                            .item(0).getNodeValue());
                    System.out.println();
                }
            }
        }catch (Exception e){}
    }

    public void update(User user){
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

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(fileXml));
        }catch (Exception e){}
    }

    public void delete(String id) {
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
                    if (element.getAttribute("id").equals(id)){
                        rootNode.removeChild(node);
                    }
                }
            }
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(fileXml));
        }catch (Exception e){}
        System.out.println("User " + id + " removed");
    }

    public List<IXmlDAO> getAll() {
        return null;
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
}
