package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepositoryJPA;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepositoryJPA;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepositoryJPA eventRepository;
    private final LocationRepositoryJPA locationRepository;

    public EventServiceImpl(EventRepositoryJPA eventRepository, LocationRepositoryJPA locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, double rating) {
        return eventRepository.searchEvents(text, rating);
    }

    @Override
    public Optional<Event> save(String name, String desc, double rating, Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location != null) {
            Event event = new Event(name, desc, rating, location);
            return Optional.of(eventRepository.save(event));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Event> findById(long id) {
        return this.eventRepository.findById(id);
    }


    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}
