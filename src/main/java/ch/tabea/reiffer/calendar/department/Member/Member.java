package ch.tabea.reiffer.calendar.department.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Member {
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
    private String lastname;

    @Column(nullable = false)
    @Size(max = 2)
    @NotEmpty
    private String gender;

    public Member() {
    }
}
