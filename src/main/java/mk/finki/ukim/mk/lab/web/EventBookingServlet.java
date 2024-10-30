package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidTicketsException;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine templateEngine;

    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine templateEngine) {
        this.eventBookingService = eventBookingService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);

        this.templateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("radio");
        String number = req.getParameter("numTickets");
        int num = Integer.parseInt(number);


        EventBooking eventBooking = null;
        try {
            eventBooking = eventBookingService.placeBooking(name, "Stefan Kolov", "127.0.0.1", num);
        } catch (InvalidTicketsException e) {
            req.getSession().setAttribute("hasError", true);
            req.getSession().setAttribute("error", e.getMessage());
            resp.sendRedirect("/event");
        }

        if (eventBooking != null) {
            IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
            WebContext webContext = new WebContext(webExchange);
            webContext.setVariable("eventBooking", eventBooking);

            this.templateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
        }
    }
}
