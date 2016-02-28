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

    @WebMethod(operationName = "add")
    @WebResult(name = "result")
    String addBook(@WebParam(name = "id") String id, @WebParam(name = "author") String author, @WebParam(name = "title") String title
            , @WebParam(name = "genre") String genre, @WebParam(name = "price") String price
            , @WebParam(name = "publishDate") String publishDate, @WebParam(name = "description") String description);

    @WebMethod(operationName = "delete")
    @WebResult(name = "result")
    String deleteBook(@WebParam(name = "id") String id);

    @WebMethod(operationName = "update")
    @WebResult(name = "result")
    String updateBook(@WebParam(name = "bookAttribute") String bookAttribute
            , @WebParam(name = "bookId") String id, @WebParam(name = "attribute") String attribute);

    @WebMethod(operationName = "getAll")
    @WebResult(name = "book")
    List<Book> getAllBooks();

    @WebMethod(operationName = "changeBook")
    @WebResult(name = "book")
    List<Book> changeBook(@WebParam(name = "book") List<Book> requestXml);
}
