package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
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
        return this.eventRepository.save(name, desc, rating, location);
    }

    @Override
    public Optional<Event> findById(long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteEvent(id);
    }
}
