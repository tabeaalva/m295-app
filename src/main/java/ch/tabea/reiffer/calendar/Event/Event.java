package ch.tabea.reiffer.calendar.Event;

import java.sql.Date;
import java.util.Set;

import ch.tabea.reiffer.calendar.Category.Category;
import ch.tabea.reiffer.calendar.Member.Member;
import ch.tabea.reiffer.calendar.Place.Place;
import jakarta.persistence.*;
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
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne(optional = true)
    @JoinColumn(name="place_id")
    Place place;

    @ManyToOne(optional = true)
    @JoinColumn(name="category_id")
    Category category;

    @ManyToMany()
    Set<Member> members;

    public Event() {
    }
}
