package mk.finki.ukim.mk.lab.service.impl;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepositoryJPA;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepositoryJPA locationRepository;

    public LocationServiceImpl(LocationRepositoryJPA locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        //addLocations();
    }

    public void addLocations() {
        Location location1 = new Location("lokacija1","address1","100","desc1");
        locationRepository.save(location1);

        Location location2 = new Location("lokacija2","address2","100","desc2");
        locationRepository.save(location2);

        Location location3 = new Location("lokacija3","address3","100","desc3");
        locationRepository.save(location3);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
