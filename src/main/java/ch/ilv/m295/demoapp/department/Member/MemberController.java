package ch.ilv.m295.demoapp.department.Event;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/member")
public class MemberController {

  @Autowired
  private MemberRepository MemberRepository;

  @Autowired
  private EventController EventController;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
    // Überprüfen Sie die Benutzeranmeldeinformationen und generieren Sie einen Authentifizierungstoken
    String authToken = authenticateUser(request.getUsername(), request.getPassword());

    // Speichern Sie den Authentifizierungstoken im Benutzerobjekt und speichern Sie ihn in der Datenbank
    User user = MemberRepository.findByUsername(request.getUsername());
    user.setAuthToken(authToken);
    MemberRepository.save(user);

    return ResponseEntity.ok(authToken);
  }

  @GetMapping("/events")
  public List<Event> getUserEvents(@RequestHeader("Authorization") String authToken) {
    // Überprüfen Sie den Authentifizierungstoken und rufen Sie den Benutzer anhand des Tokens ab
    User user = authenticateToken(authToken);

    // Rufen Sie die Kalenderereignisse des Benutzers aus dem EventController ab und geben Sie sie zurück
    return EventController.getUserEvents(user.getId());
  }

  private String authenticateUser(String username, String password) {
    // Überprüfen Sie die Benutzeranmeldeinformationen und generieren Sie einen Authentifizierungstoken
    // Hier könnte z.B. eine externe Authentifizierungsschnittstelle wie Spring Security verwendet werden
    // Das genaue Verfahren hängt von den Anforderungen Ihrer Anwendung ab
    String authToken = "xyz123";

    return authToken;
  }

  private User authenticateToken(String authToken) {
    // Überprüfen Sie den Authentifizierungstoken und rufen Sie den Benutzer anhand des Tokens ab
    User user = MemberRepository.findByAuthToken(authToken);
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid auth token");
    }

    return user;
  }

}


