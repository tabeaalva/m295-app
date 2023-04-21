package ch.ilv.m295.demoapp.department.Member;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ch.ilv.m295.demoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
@SecurityRequirement(name = "bearerAuth")

@RestController
@Validated
public class MemberController {

    private final MemberService MemberService;

    MemberController(MemberService MemberService) {
        this.MemberService = MemberService;
    }

    @GetMapping("api/Member")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Member>> all() {
        List<Member> result = MemberService.getMembers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/Member/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Member> one(@PathVariable Long id) {
        Member Member = MemberService.getMember(id);
        return new ResponseEntity<>(Member, HttpStatus.OK);
    }

    @PostMapping("api/Member")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Member> newMember(@Valid @RequestBody Member Member) {
        Member savedMember = MemberService.insertMember(Member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    @PutMapping("api/Member/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Member> updateMember(@Valid @RequestBody Member Member, @PathVariable Long id) {
        Member savedMember = MemberService.updateMember(Member, id);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    @DeleteMapping("api/Member/{id}")
    @RolesAllowed(Roles.Admin)
    public void deleteMember(@PathVariable Long id) {
        MemberService.deleteMember(id);
    }
}



