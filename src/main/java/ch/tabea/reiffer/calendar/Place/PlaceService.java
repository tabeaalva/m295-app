package ch.tabea.reiffer.calendar.Place;

import org.springframework.stereotype.Service;

import ch.tabea.reiffer.calendar.storage.EntityNotFoundException;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository repository;

    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public List<Place> getPlaces() {
        return repository.findByOrderByNameAsc();
    }

    public Place getPlace(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Place.class));
    }

    public Place insertPlace(Place Place) {
        return repository.save(Place);
    }

    public Place updatePlace(Place Place, Long id) {
        return repository.findById(id)
                .map(PlaceOrig -> {
                    PlaceOrig.setName(Place.getName());
                    return repository.save(PlaceOrig);
                })
                .orElseGet(() -> repository.save(Place));
    }

    public void deletePlace(Long id) {
        repository.deleteById(id);
    }
}
