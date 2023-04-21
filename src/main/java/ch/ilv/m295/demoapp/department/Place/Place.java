package ch.ilv.m295.demoapp.department.Place;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Place {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String place_name;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String description;

    public Place() {
    }
}
