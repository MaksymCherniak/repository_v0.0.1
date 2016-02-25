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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlBookDAO implements IBookDAO {
    private static Logger log = Logger.getLogger(XmlBookDAO.class.getName());
    private static File fileXml = new File("books.xml");
    private DocumentBuilder documentBuilder;
    private Document doc;
    private boolean idChecker = false;

    public XmlBookDAO() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
        }
        if (!fileXml.exists()) {
            createXmlFile();
        }
    }

    public void addBook(Book book) {
        try {
            doc = documentBuilder.parse(fileXml);
            Node rootNode = doc.getFirstChild();

            Element bookElement = doc.createElement("book");
            rootNode.appendChild(bookElement);
            bookElement.setAttribute("id", String.valueOf(book.getId()));

            Element authorElement = doc.createElement("author");
            authorElement.appendChild(doc.createTextNode(book.getAuthor()));
            bookElement.appendChild(authorElement);

            Element titleElement = doc.createElement("title");
            titleElement.appendChild(doc.createTextNode(book.getTitle()));
            bookElement.appendChild(titleElement);

            Element genreElement = doc.createElement("genre");
            genreElement.appendChild(doc.createTextNode(book.getGenre()));
            bookElement.appendChild(genreElement);

            Element priceElement = doc.createElement("price");
            priceElement.appendChild(doc.createTextNode(String.valueOf(book.getPrice())));
            bookElement.appendChild(priceElement);

            Element publishDateElement = doc.createElement("publish_date");
            publishDateElement.appendChild(doc.createTextNode(String.valueOf(book.getPublishDate())));
            bookElement.appendChild(publishDateElement);

            Element descriptionElement = doc.createElement("description");
            descriptionElement.appendChild(doc.createTextNode(book.getDescription()));
            bookElement.appendChild(descriptionElement);

            initTransformer();
            log.info(book.toString() + "\nBook added.");
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
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
                    if (element.getAttribute("id").equals(id)) {
                        rootNode.removeChild(node);
                        return true;
                    }
                }
            }
            initTransformer();
            log.info("Book " + id + " deleted");
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
                    book.setIndex(element.getAttribute("id"));
                    book.setAuthor(element.getElementsByTagName("author").item(0).getChildNodes().item(0).getNodeValue());
                    book.setTitle(element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue());
                    book.setGenre(element.getElementsByTagName("genre").item(0).getChildNodes().item(0).getNodeValue());
                    book.setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getChildNodes().item(0).getNodeValue()));
                    book.setDescription(element.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue());
                    String publishDate = element.getElementsByTagName("publish_date").item(0).getChildNodes().item(0).getNodeValue();
                    String[] partsOfDate = publishDate.split("-");
                    book.setPublishDate(LocalDate.of(Integer.parseInt(partsOfDate[0]),
                            Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2])));
                    listOfBooks.add(book);
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        return listOfBooks;
    }

    public Book findBookById(String id){
        Element bookElement = findBookElement(id);
        Book book = new Book();
        book.setIndex(bookElement.getAttribute("id"));
        book.setAuthor(bookElement.getElementsByTagName("author").item(0).getChildNodes().item(0).getNodeValue());
        book.setTitle(bookElement.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue());
        book.setGenre(bookElement.getElementsByTagName("genre").item(0).getChildNodes().item(0).getNodeValue());
        book.setPrice(Double.parseDouble(bookElement.getElementsByTagName("price").item(0).getChildNodes().item(0).getNodeValue()));
        book.setDescription(bookElement.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue());
        String publishDate = bookElement.getElementsByTagName("publish_date").item(0).getChildNodes().item(0).getNodeValue();
        String[] partsOfDate = publishDate.split("-");
        book.setPublishDate(LocalDate.of(Integer.parseInt(partsOfDate[0]),
                Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2])));
        return book;
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
                    if (element.getAttribute("id").equals(id)) {
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
        if (!checkId(id)) {
            return;
        }
        Element book = findBookElement(id);
        switch (bookAttribute) {
            case AUTHOR:
                book.getElementsByTagName("author").item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Author changed to \"" + attribute + "\" in the book with id: " + book.getAttribute("id"));
                break;
            case DESCRIPTION:
                book.getElementsByTagName("description").item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Description changed to \"" + attribute + "\" in the book with id: " + book.getAttribute("id"));
                break;
            case TITLE:
                book.getElementsByTagName("title").item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Title changed to \"" + attribute + "\" in the book with id: " + book.getAttribute("id"));
                break;
            case PUBLISH_DATE:
                book.getElementsByTagName("publish_date").item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Publish date changed to \"" + attribute + "\" in the book with id: " + book.getAttribute("id"));
                break;
            case GENRE:
                book.getElementsByTagName("genre").item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Genre changed to \"" + attribute + "\" in the book with id: " + book.getAttribute("id"));
                break;
            case PRICE:
                book.getElementsByTagName("price").item(0).getChildNodes().item(0).setNodeValue(attribute);
                initTransformer();
                log.info("Price changed to \"" + attribute + "\" in the book with id: " + book.getAttribute("id"));
                break;
        }
    }

    public void setId() {
        try {
            doc = documentBuilder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfBooks = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            Node lastBook = listOfBooks.item(listOfBooks.getLength() - 1);
            if (lastBook.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) lastBook;
                Book.setId(Integer.parseInt(element.getAttribute("id")));
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
    }

    private boolean checkId(String id) {
        try {
            doc = documentBuilder.parse(fileXml);
            doc.getDocumentElement().normalize();

            NodeList listOfBooks = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i = 0; i < listOfBooks.getLength(); i++) {
                Node node = listOfBooks.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("id").equals(id)) {
                        idChecker = true;
                        return idChecker;
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.INFO, "Exception: ", e);
        }
        log.info("Book not found");
        return idChecker;
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
