package ch.ilv.m295.demoapp.department.Event;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Event {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String event_name;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private Date date;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private Time time;

    public Event() {
    }
}