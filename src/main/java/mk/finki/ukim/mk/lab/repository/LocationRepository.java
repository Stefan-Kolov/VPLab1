package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    List<Location> locations = new ArrayList<>();
    {
        locations.add(new Location("Skopje", "Centar", "5","desc"));
        locations.add(new Location("Skopje", "Aerodrom", "5","desc"));
        locations.add(new Location("Skopje", "Karpos", "5","desc"));
        locations.add(new Location("Skopje", "Kisela Voda", "5","desc"));
        locations.add(new Location("Skopje", "Vodno", "5","desc"));
    }

    public List<Location> findAll() {
        return locations;
    }

    public Optional<Location> findById(Long id){
        return locations.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
