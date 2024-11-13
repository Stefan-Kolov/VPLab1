package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        List<Event> eventList = eventService.listAll();
        model.addAttribute("eventsList", eventList);
        model.addAttribute("hasError", true);
        model.addAttribute("error", error);
        return "listEvents";
    }

    //@DeleteMapping("/delete/{id}")
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model){
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locationList", locationList);
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
                               Model model) {
        List<Event> eventList = eventService.listAll();
        List<Event> filteredEvents = eventService.searchEvents(text, rating);
        model.addAttribute("events", filteredEvents);
        model.addAttribute("eventsList",eventList);
        return "listEvents";
    }


}
