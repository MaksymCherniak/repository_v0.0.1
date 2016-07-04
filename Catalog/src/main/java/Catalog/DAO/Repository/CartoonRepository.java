package Catalog.DAO.Repository;

import Catalog.Controller.AppStaticValues;
import Catalog.Entity.Cartoon;
import Catalog.Entity.Enum.GenreFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartoonRepository extends JpaRepository<Cartoon, Long> {
    @Query("SELECT c FROM Cartoon c WHERE user_id LIKE :user_id")
    List<Cartoon> getCartoonsByUserId(@Param(AppStaticValues.USER_ID) long user_id);
}
