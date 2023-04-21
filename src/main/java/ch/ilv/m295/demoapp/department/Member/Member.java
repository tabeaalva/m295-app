package ch.ilv.m295.demoapp.department.Member;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String member_name;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String member_lastname;

    @Column(nullable = false)
    @Size(max = 2)
    @NotEmpty
    private String gender;

    public Member() {
    }
}
