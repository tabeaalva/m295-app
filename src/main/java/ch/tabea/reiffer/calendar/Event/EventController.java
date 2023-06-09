package ch.tabea.reiffer.calendar.Event;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ch.tabea.reiffer.calendar.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
@SecurityRequirement(name = "bearerAuth")

@RestController
@Validated
public class EventController {

    private final EventService EventService;

    EventController(EventService EventService) {
        this.EventService = EventService;
    }

    @GetMapping("api/event")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Event>> all() {
        List<Event> result = EventService.getEvents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/event/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Event> one(@PathVariable Long id) {
        Event Event = EventService.getEvent(id);
        return new ResponseEntity<>(Event, HttpStatus.OK);
    }

    @PostMapping("api/event")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Event> newEvent(@Valid @RequestBody Event Event) {
        Event savedEvent = EventService.insertEvent(Event);
        return new ResponseEntity<>(savedEvent, HttpStatus.OK);
    }

    @PutMapping("api/event/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Event> updateEvent(@Valid @RequestBody Event Event, @PathVariable Long id) {
        Event savedEvent = EventService.updateEvent(Event, id);
        return new ResponseEntity<>(savedEvent, HttpStatus.OK);
    }

    @DeleteMapping("api/event/{id}")
    @RolesAllowed(Roles.Update)
    public void deleteEvent(@PathVariable Long id) {
        EventService.deleteEvent(id);
    }
}
