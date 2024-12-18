package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidTicketsException;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.impl.AuthServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final AuthServiceImpl authService;
    public EventBookingController(EventBookingService eventBookingService, AuthServiceImpl authService) {
        this.eventBookingService = eventBookingService;
        this.authService = authService;
    }

    @GetMapping
    public String showBookingConfirmationPage(Model model) {
        return "bookingConfirmation";
    }

    @PostMapping
    public String createBooking(
            @RequestParam("radio") String name,
            @RequestParam("numTickets") int numTickets,
            HttpServletRequest request,
            Model model) {
        try {
            String user = request.getRemoteUser();
            User u = authService.findByUsername(user);
            EventBooking eventBooking = eventBookingService.placeBooking(name, user, "127.0.0.1", numTickets);
            u.addEvent(eventBooking);
            List<EventBooking> bookingList = u.getEvents();
            model.addAttribute("eventBooking", eventBooking);
            model.addAttribute("userBookings", bookingList);
            model.addAttribute("username",user);
            return "bookingConfirmation";
        } catch (InvalidTicketsException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "redirect:/event";
        }
    }
}
