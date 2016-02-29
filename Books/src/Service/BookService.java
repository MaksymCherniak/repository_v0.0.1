package Service;

import Entity.Book;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface BookService {
    String ID = "id";
    String AUTHOR = "author";
    String TITLE = "title";
    String GENRE = "genre";
    String PRICE = "price";
    String PUBLISH_DATE = "publish_date";
    String DESCRIPTION = "description";
    String BOOK = "book";

    @WebMethod(operationName = "add")
    @WebResult(name = "result")
    String addBook(@WebParam(name = ID) String id, @WebParam(name = AUTHOR) String author, @WebParam(name = TITLE) String title
            , @WebParam(name = GENRE) String genre, @WebParam(name = PRICE) String price
            , @WebParam(name = PUBLISH_DATE) String publishDate, @WebParam(name = DESCRIPTION) String description);

    @WebMethod(operationName = "delete")
    @WebResult(name = "result")
    String deleteBook(@WebParam(name = ID) String id);

    @WebMethod(operationName = "update")
    @WebResult(name = "result")
    String updateBook(@WebParam(name = "bookAttribute") String bookAttribute
            , @WebParam(name = "bookId") String id, @WebParam(name = "attribute") String attribute);

    @WebMethod(operationName = "getAll")
    @WebResult(name = BOOK)
    List<Book> getAllBooks();

    @WebMethod(operationName = "changeBook")
    @WebResult(name = BOOK)
    List<Book> changeBook(@WebParam(name = BOOK) List<Book> requestXml);
}
