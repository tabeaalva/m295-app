package ch.ilv.m295.demoapp.department.Event;

import org.springframework.stereotype.Service;

import ch.ilv.m295.demoapp.storage.EntityNotFoundException;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getEvents() {
        return repository.findByOrderByNameAsc();
    }

    public Event getEvent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Event.class));
    }

    public Event insertEvent(Event Event) {
        return repository.save(Event);
    }

    public Event updateEvent(Event Event, Long id) {
        return repository.findById(id)
                .map(EventOrig -> {
                    EventOrig.setName(Event.getName());
                    return repository.save(EventOrig);
                })
                .orElseGet(() -> repository.save(Event));
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
