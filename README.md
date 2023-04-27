# Kalender Backend-Applikation mit Spring Boot

Dieses Projekt beinhaltet die Entwicklung einer Java-Backend-Applikation mit Spring Boot, die einen Kalender bereitstellt. Es wird eine PostgreSQL-Datenbank über JPA angebunden, um Kalenderereignisse zu speichern. Die REST-Controller implementieren die grundlegenden CRUD-Operationen und werden über OAuth2 mit einem Keycloak-Server abgesichert.

## Funktionen der Applikation

Die Applikation bietet folgende Funktionen:

1. Anzeigen von Kalenderereignissen
2. Erstellen von Kalenderereignissen
3. Aktualisieren von Kalenderereignissen
4. Löschen von Kalenderereignissen
5. Ort
6. Kategorien
7. Member
8. Authentifizierung
9. Postman
10. Absicherung der REST-Controller
11. Integration von Swagger UI
12. Ablage auf GitHub

## Technologien

Die Applikation nutzt folgende Technologien:

- Java 11
- Spring Boot
- PostgreSQL
- JPA
- OAuth2
- Keycloak
- Swagger UI

## Installation

1. Klone das Repository: `git clone https://github.com/<username>/<repository>.git`
2. Stelle sicher, dass du eine PostgreSQL-Datenbank hast und die Zugangsdaten in der Datei `application.properties` konfiguriert sind.
3. Starte die Applikation: `./mvnw spring-boot:run`
4. Öffne `http://localhost:8080/swagger-ui.html`
