package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EventRepository {
    Location location = new Location("Skopje","Skopje","5","desc");
    List<Event> events = new ArrayList<>();
    {
        events.add(new Event("Koncert", "Opis", 1, location));
        events.add(new Event("Koncert1", "Opis1", 2, location));
        events.add(new Event("Koncert2", "Opis2", 3, location));
        events.add(new Event("Koncert3", "Opis3", 4, location));
        events.add(new Event("Koncert4", "Opis4", 5, location));
        events.add(new Event("Koncert5", "Opis5", 6, location));
        events.add(new Event("Koncert6", "Opis6", 7, location));
        events.add(new Event("Koncert7", "Opis7", 8, location));
        events.add(new Event("Koncert8", "Opis8", 9, location));
        events.add(new Event("Koncert9", "Opis9", 10, location));
        events.add(new Event("Koncert10", "Opis10", 11, location));
        // Add remaining events similarly
    }


    public List<Event> findAll(){
        return events;
    }

    public List<Event> searchEvents(String text,double rating){
        return events.stream().filter(i -> i.getName().contains(text) && i.getPopularityScore() >= rating).toList();
    }

    public void deleteEvent(Long id){
        events.removeIf(i -> Objects.equals(i.getId(), id));
    }

    public Optional<Event> save(String name, String desc, Double rating, Location location) {
        events.removeIf(i-> i.getName().equals(name));

        Event event = new Event(name, desc, rating, location);
        events.add(event);
        return Optional.of(event);
    }

    public Optional<Event> findById(long id) {
        return events.stream().filter(event -> event.getId() == id).findFirst();
    }
}
