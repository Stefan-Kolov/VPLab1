package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

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
            double rating = Double.parseDouble(req.getParameter("rating"));
            req.getSession().setAttribute("eventList", eventService.searchEvents(searchText, rating));
            resp.sendRedirect("/event");
        }
    }
}
