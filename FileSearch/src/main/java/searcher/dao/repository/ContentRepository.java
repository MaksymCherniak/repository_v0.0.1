package searcher.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import searcher.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    @Query("SELECT u FROM Content u WHERE u.content LIKE :content AND u.creationDate LIKE :creationDate")
    Content getByContentAndDate(@Param("content") String content, @Param("creationDate") String creationDate);
}
