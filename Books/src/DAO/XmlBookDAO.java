package DAO;

import Entity.Book;
import Entity.BookAttribute;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlBookDAO implements IBookDAO {
    private static Logger log = Logger.getLogger(XmlBookDAO.class.getName());
    private File fileXml = new File("books.xml");
    private DocumentBuilder documentBuilder;
    private Document doc;

    public XmlBookDAO() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        if (!fileXml.exists()) {
            createXmlFile();
        }
    }

    public List<Book> changeBook(List<Book> requestXml) {
        for (Book book : requestXml) {
            if (findBookById(book.getIndex()) == null) {
                addBook(book);
            } else {
                deleteBook(book.getIndex());
                addBook(book);
            }
        }
        return getAllBooks();
    }

    public boolean addBook(Book book) {
        if (findBookById(book.getIndex()) != null) {
            return false;
        }
        try {
            doc = documentBuilder.parse(fileXml);
            Node rootNode = doc.getFirstChild();

            Element bookElement = doc.createElement("book");
            rootNode.appendChild(bookElement);
            bookElement.setAttribute(ID, book.getIndex());

            Element authorElement = doc.createElement(AUTHOR);
            authorElement.appendChild(doc.createTextNode(book.getAuthor()));
            bookElement.appendChild(authorElement);

            Element titleElement = doc.createElement(TITLE);
            titleElement.appendChild(doc.createTextNode(book.getTitle()));
            bookElement.appendChild(titleElement);

            Element genreElement = doc.createElement(GENRE);
            genreElement.appendChild(doc.createTextNode(book.getGenre()));
            bookElement.appendChild(genreElement);

            Element priceElement = doc.createElement(PRICE);
            priceElement.appendChild(doc.createTextNode(String.valueOf(book.getPrice())));
            bookElement.appendChild(priceElement);

            Element publishDateElement = doc.createElement(PUBLISH_DATE);
            publishDateElement.appendChild(doc.createTextNode(String.valueOf(book.getPublishDate())));
            bookElement.appendChild(publishDateElement);

            Element descriptionElement = doc.createElement(DESCRIPTION);
            descriptionElement.appendChild(doc.createTextNode(book.getDescription()));
            bookElement.appendChild(descriptionElement);

            initTransformer();
            log.info(book.toString() + "\nBook added.");
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public boolean deleteBook(String id) {
        try {
            doc = documentBuilder.parse(fileXml);
            Node rootNode = doc.getFirstChild();
            doc.getDocumentElement().normalize();

            NodeList listOfBooks = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfBooks.getLength(); i++) {
                Node node = listOfBooks.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(ID).equals(id)) {
                        rootNode.removeChild(node);
                        initTransformer();
                        log.info("Book " + id + " deleted");
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return false;
    }

    public List<Book> getAllBooks() {
        List<Book> listOfBooks = new ArrayList<Book>();
        try {
            doc = documentBuilder.parse(fileXml);
            doc.getDocumentElement().normalize();
            NodeList booksList = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());

            for (int i = 0; i < booksList.getLength(); i++) {
                Node node = booksList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Book book = new Book();
                    book.setIndex(element.getAttribute(ID));
                    book.setAuthor(element.getElementsByTagName(AUTHOR).item(0).getChildNodes().item(0).getNodeValue());
                    book.setTitle(element.getElementsByTagName(TITLE).item(0).getChildNodes().item(0).getNodeValue());
                    book.setGenre(element.getElementsByTagName(GENRE).item(0).getChildNodes().item(0).getNodeValue());
                    book.setPrice(element.getElementsByTagName(PRICE).item(0).getChildNodes().item(0).getNodeValue());
                    book.setDescription(element.getElementsByTagName(DESCRIPTION).item(0).getChildNodes().item(0).getNodeValue());
                    book.setPublishDate(element.getElementsByTagName(PUBLISH_DATE).item(0).getChildNodes().item(0).getNodeValue());
                    listOfBooks.add(book);
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return listOfBooks;
    }

    public Book findBookById(String id) {
        Element bookElement = findBookElement(id);
        if (bookElement != null) {
            Book book = new Book();
            book.setIndex(bookElement.getAttribute(ID));
            book.setAuthor(bookElement.getElementsByTagName(AUTHOR).item(0).getChildNodes().item(0).getNodeValue());
            book.setTitle(bookElement.getElementsByTagName(TITLE).item(0).getChildNodes().item(0).getNodeValue());
            book.setGenre(bookElement.getElementsByTagName(GENRE).item(0).getChildNodes().item(0).getNodeValue());
            book.setPrice(bookElement.getElementsByTagName(PRICE).item(0).getChildNodes().item(0).getNodeValue());
            book.setDescription(bookElement.getElementsByTagName(DESCRIPTION).item(0).getChildNodes().item(0).getNodeValue());
            book.setPublishDate(bookElement.getElementsByTagName(PUBLISH_DATE).item(0).getChildNodes().item(0).getNodeValue());
            return book;
        }
        log.info("Book isn't found");
        return null;
    }

    public Element findBookElement(String id) {
        try {
            doc = documentBuilder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfBooks = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfBooks.getLength(); i++) {
                Node node = listOfBooks.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(ID).equals(id)) {
                        return element;
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return null;
    }

    public void updateBook(BookAttribute bookAttribute, String id, String attribute) {
        if (findBookById(id) == null) {
            return;
        }
        Element book = findBookElement(id);
        switch (bookAttribute) {
            case AUTHOR:
                book.getElementsByTagName(AUTHOR).item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Author changed to \"" + attribute + "\" in the book with id: " + book.getAttribute(ID));
                break;
            case DESCRIPTION:
                book.getElementsByTagName(DESCRIPTION).item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Description changed to \"" + attribute + "\" in the book with id: " + book.getAttribute(ID));
                break;
            case TITLE:
                book.getElementsByTagName(TITLE).item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Title changed to \"" + attribute + "\" in the book with id: " + book.getAttribute(ID));
                break;
            case PUBLISH_DATE:
                book.getElementsByTagName(PUBLISH_DATE).item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Publish date changed to \"" + attribute + "\" in the book with id: " + book.getAttribute(ID));
                break;
            case GENRE:
                book.getElementsByTagName(GENRE).item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Genre changed to \"" + attribute + "\" in the book with id: " + book.getAttribute(ID));
                break;
            case PRICE:
                book.getElementsByTagName(PRICE).item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Price changed to \"" + attribute + "\" in the book with id: " + book.getAttribute(ID));
                break;
        }
    }

    private void createXmlFile() {
        try {
            doc = documentBuilder.newDocument();

            Element rootElement = doc.createElement("catalog");
            doc.appendChild(rootElement);

            initTransformer();
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }

    private void initTransformer() {
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(fileXml);
            t.transform(new DOMSource(doc), result);
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }
}
