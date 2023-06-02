package ch.tabea.reiffer.calendar.Place;

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
public class PlaceController {

    private final PlaceService PlaceService;

    PlaceController(PlaceService PlaceService) {
        this.PlaceService = PlaceService;
    }

    @GetMapping("api/place")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Place>> all() {
        List<Place> result = PlaceService.getPlaces();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/place/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Place> one(@PathVariable Long id) {
        Place Place = PlaceService.getPlace(id);
        return new ResponseEntity<>(Place, HttpStatus.OK);
    }

    @PostMapping("api/place")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Place> newPlace(@Valid @RequestBody Place Place) {
        Place savedPlace = PlaceService.insertPlace(Place);
        return new ResponseEntity<>(savedPlace, HttpStatus.OK);
    }

    @PutMapping("api/place/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Place> updatePlace(@Valid @RequestBody Place Place, @PathVariable Long id) {
        Place savedPlace = PlaceService.updatePlace(Place, id);
        return new ResponseEntity<>(savedPlace, HttpStatus.OK);
    }

    @DeleteMapping("api/place/{id}")
    @RolesAllowed(Roles.Admin)
    public void deletePlace(@PathVariable Long id) {
        PlaceService.deletePlace(id);
    }
}

