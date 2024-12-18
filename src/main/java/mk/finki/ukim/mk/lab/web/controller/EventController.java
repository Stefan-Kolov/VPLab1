package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.LocationServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventServiceImpl eventService;
    private final LocationServiceImpl locationService;

    public EventController(EventServiceImpl eventService, LocationServiceImpl locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req){
        // Fetch data
        String username = req.getRemoteUser();
        List<Event> eventList = eventService.listAll();
        model.addAttribute("eventsList", eventList);
        model.addAttribute("hasError", true);
        model.addAttribute("error", error);
        model.addAttribute("username", username);
        return "listEvents";
    }

    @GetMapping("/access_denied")
    public String getAccessDenied(Model model) {
        return "access-denied";
    }

    //@DeleteMapping("/delete/{id}")
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id){
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddEventPage(Model model){
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations", locationList);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String desc,
                            @RequestParam double rating,
                            @RequestParam Long location){
        this.eventService.save(name, desc, rating, location);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditEventForm(@PathVariable Long id, Model model){
        if(this.eventService.findById(id).isPresent()){
            Event event = this.eventService.findById(id).get();
            List<Location> locationList = locationService.findAll();
            model.addAttribute("event", event);
            model.addAttribute("locations", locationList);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @PostMapping("/search")
    public String searchEvents(@RequestParam(required = false) String text,
                               @RequestParam(required = false) Double rating,
                               @RequestParam(required = false) Long locationId,
                               Model model) {
        List<Event> eventList = eventService.listAll();
        List<Event> filteredEvents = eventService.searchEvents(text, rating);
        model.addAttribute("events", filteredEvents);
        model.addAttribute("eventsList",eventList);
        return "listEvents";
    }
}

//INSERT INTO LOCATION (ID, ADDRESS, CAPACITY, DESCRIPTION, NAME)
//VALUES (1, '123 Main Street', 100, 'A large conference hall', 'Conference Center');
//INSERT INTO LOCATION (ID, ADDRESS, CAPACITY, DESCRIPTION, NAME)
//VALUES (2, '456 Elm Street', 50, 'Small meeting room', 'Meeting Room A');
//localhost:9091