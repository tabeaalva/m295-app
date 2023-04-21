package ch.ilv.m295.demoapp.department.Event;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private Date startDate;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private Date endDate;

    public Event() {
    }
}
