package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidTicketsException;
import mk.finki.ukim.mk.lab.service.EventBookingService;
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

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
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
            User user = (User) request.getSession().getAttribute("user");
            EventBooking eventBooking = eventBookingService.placeBooking(name, user.getName(), "127.0.0.1", numTickets);
            user.addEvent(eventBooking);
            List<EventBooking> bookingList = user.getEvents();
            model.addAttribute("eventBooking", eventBooking);
            model.addAttribute("userBookings", bookingList);
            return "bookingConfirmation";
        } catch (InvalidTicketsException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "redirect:/event";
        }
    }
}
