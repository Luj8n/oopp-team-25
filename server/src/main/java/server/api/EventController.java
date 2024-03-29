package server.api;

import commons.Event;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.service.EventService;

/**
 * Controller for Event [CONT -> SERV -> REPO].
 */
@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Returns all persons in the database,
     * if no people returns empty list.
     *
     * @return list of persons
     */
    // TODO move this to admin
    @GetMapping(path = "/event")
    public List<Event> getAllEvents() {
        return eventService.getAllEvent();
    }

    /**
     * Returns an event based on its invite code.
     *
     * @param code the invite code
     * @return the searched event
     */
    @GetMapping(path = "/event/{code}")
    public Event getEventByCode(@PathVariable String code) {
        return eventService.getEventByCode(code);
    }

    @PostMapping(path = "/event")
    public void createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
    }
}
