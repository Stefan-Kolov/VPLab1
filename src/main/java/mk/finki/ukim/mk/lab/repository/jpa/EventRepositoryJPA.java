package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepositoryJPA extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    @Query("SELECT e FROM Event e WHERE (:keyword IS NULL OR e.name LIKE %:keyword%) AND (:rating IS NULL OR e.popularityScore >= :rating)")
    List<Event> searchEvents(@Param("keyword") String keyword, @Param("rating") Double rating);

}
