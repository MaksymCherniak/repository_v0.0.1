package searcher.mainClasses;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import searcher.dao.service.IContentService;
import searcher.entity.Content;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;

public class XmlParser implements AppStaticValues {
    private static Logger log = Logger.getLogger(XmlParser.class.getName());
    private IContentService iContentService = ApplicationContext.getCtx().getBean("IContentService", IContentService.class);
    private DocumentBuilder documentBuilder = ApplicationContext.getCtx().getBean("documentBuilder", DocumentBuilder.class);
    private Document doc;

    public XmlParser() {
    }

    public boolean checkXmlFileContent(File fileXml) {
        try {
            doc = documentBuilder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfElements = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfElements.getLength(); i++) {
                Node node = listOfElements.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName(CONTENT) != null && element.getElementsByTagName(CREATION_DATE) != null
                            && element.getElementsByTagName(CONTENT).item(0).getChildNodes().item(0).getNodeValue()
                            .toCharArray().length < 1024) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void addContentFromXmlFile(File fileXml) {
        try {
            doc = documentBuilder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfElements = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfElements.getLength(); i++) {
                Node node = listOfElements.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Content content = new Content();
                    content.setContent(element.getElementsByTagName(CONTENT).item(0).getChildNodes().item(0).getNodeValue());
                    content.setCreationDate(element.getElementsByTagName(CREATION_DATE).item(0).getChildNodes().item(0).getNodeValue());
                    if (iContentService.addContent(content)) {
                        log.info("\n" + content.toString() + "\nAdded to database\n------------------------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.WARN, "Exception: ", e);
        }
    }

    public void moveXmlFile(File source, File dest) {
        source.renameTo(new File(dest, source.getName()));
    }
}
