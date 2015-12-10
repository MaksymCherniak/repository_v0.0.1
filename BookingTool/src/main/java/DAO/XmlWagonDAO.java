package DAO;

import Model.LocalModel.AvailabilitySeats;
import Model.LocalModel.User;
import Model.LocalModel.Wagon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Max on 10.12.2015.
 */
public class XmlWagonDAO implements IWagonDAO{
    private final File fileXml = new File("Wagon.xml");
    private boolean seatChecker = false;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private Wagon wagon;
    public XmlWagonDAO(Wagon wagon){
        this.wagon = wagon;
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {}
        if (!fileXml.exists()) {
            createXmlFile();
            insertWagon(wagon);
        }
    }

    public void createXmlFile() {
        try {
            doc = builder.newDocument();

            Element rootElement = doc.createElement("Wagon");
            doc.appendChild(rootElement);

            initTransformer();
        } catch (Exception e) {}
    }

    public boolean insertWagon(Wagon wagon) {
        int[] seats = wagon.getSeatChecker();
        try {
            doc = builder.parse(fileXml);
            Node rootNode = doc.getFirstChild();

            for (int i = 0; i < seats.length; i++) {
                Element userElement = doc.createElement("Seat");
                rootNode.appendChild(userElement);
                userElement.setAttribute("seatNumber", String.valueOf(seats[i]));
                userElement.setAttribute("status", String.valueOf(AvailabilitySeats.FREE));
            }

            initTransformer();
        } catch (Exception e) { return false; }
        return true;
    }

    public boolean insertSeat(int seatNumber, int userId) {
        try {
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfSeats = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfSeats.getLength(); i++) {
                Node node = listOfSeats.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("seatNumber").equals(String.valueOf(seatNumber))) {
                        element.setAttribute("status", String.valueOf(AvailabilitySeats.OCCUPIED));
                        element.setAttribute("userId", String.valueOf(userId));
                        initTransformer();
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void deleteWagon(String number) {

    }

    public Wagon findWagon(int number) {
        return null;
    }

    public boolean updateWagon(int userId) {
        try {
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfSeats = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfSeats.getLength(); i++) {
                Node node = listOfSeats.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("userId").equals(String.valueOf(userId))) {
                        element.setAttribute("status", String.valueOf(AvailabilitySeats.FREE));
                        element.removeAttribute("userId");
                        System.out.println("Seat number " + element.getAttribute("seatNumber") + " is FREE now");
                        initTransformer();
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void printWagon() {
        try{
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();
            NodeList seatsList = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < seatsList.getLength(); i++){
                Node node = seatsList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                    System.out.print("Seat number " + element.getAttribute("seatNumber") + " is " + element.getAttribute("status"));
                    System.out.print(" ");
                    if (!element.getAttribute("userId").equals("")) {
                        System.out.print(" by user with " + element.getAttribute("userId") + " ID");
                    }
                    System.out.println();
                }
            }
        }catch (Exception e){}
    }

    private void initTransformer(){
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(fileXml));
        }catch (Exception e){}
    }
    public boolean checkSeatAvailable(int seatNumber) {
        try {
            doc = builder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfSeats = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfSeats.getLength(); i++) {
                Node node = listOfSeats.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("seatNumber").equals(String.valueOf(seatNumber)) &&
                            element.getAttribute("status").equals(String.valueOf(AvailabilitySeats.FREE))) {
                        seatChecker = true;
                        return seatChecker;
                    }
                }
            }
        } catch (Exception e) {}
        return seatChecker;
    }
}
