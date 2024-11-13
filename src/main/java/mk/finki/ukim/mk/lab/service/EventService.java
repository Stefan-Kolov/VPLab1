package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, double rating);
    Optional<Event> save(String name, String desc, double rating, Long locationId);
    Optional<Event> findById(long id);
    void deleteById(long id);
}
