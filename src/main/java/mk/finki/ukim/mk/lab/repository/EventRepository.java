package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EventRepository {
    List<Event> events = List.of(new Event("Koncert", "Opis", 1),
            new Event("Koncert1", "Opis1", 2),
            new Event("Koncert2", "Opis2", 3),
            new Event("Koncert3", "Opis3", 4),
            new Event("Koncert4", "Opis4", 5),
            new Event("Koncert5", "Opis5", 6),
            new Event("Koncert6", "Opis6", 7),
            new Event("Koncert7", "Opis7", 8),
            new Event("Koncert8", "Opis8", 9),
            new Event("Koncert9", "Opis9", 10),
            new Event("Koncert10", "Opis10", 11));

    public List<Event> findAll(){
        return events;
    }

    public List<Event> searchEvents(String text,double rating){
        return events.stream().filter(i -> i.getName().contains(text) && i.getPopularityScore() >= rating).toList();
    }
}
