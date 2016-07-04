package Catalog.DAO.Repository;

import Catalog.Controller.AppStaticValues;
import Catalog.Entity.Book;
import Catalog.Entity.Enum.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE user_id LIKE :user_id")
    List<Book> getBooksByUserId(@Param(AppStaticValues.USER_ID) long user_id);

    @Query("SELECT b FROM Book b WHERE b.genreBook LIKE :genreBook")
    List<Book> getBooksByGenre(@Param(AppStaticValues.BOOK_GENRE) GenreBook genreBook);
}
