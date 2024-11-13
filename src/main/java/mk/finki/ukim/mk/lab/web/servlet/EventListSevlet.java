package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = {"","/event"})
public class EventListSevlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListSevlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("list", eventService.listAll());

        this.templateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchText = req.getParameter("text");
        if (!searchText.isEmpty()) {
            String ratingNum = req.getParameter("rating");
            double rating = 0;
            if (!ratingNum.isEmpty()){
                rating = Double.parseDouble(ratingNum);
            }

            IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
            WebContext context = new WebContext(webExchange);
            List<Event> searchEvents = eventService.searchEvents(searchText, rating);
            context.setVariable("list", eventService.listAll());
            context.setVariable("events", searchEvents);

            this.templateEngine.process("listEvents.html", context, resp.getWriter());
        }
    }
}
