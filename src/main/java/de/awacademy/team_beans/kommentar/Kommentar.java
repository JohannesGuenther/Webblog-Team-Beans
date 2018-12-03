package de.awacademy.team_beans.kommentar;
import de.awacademy.team_beans.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Kommentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType Identity -> ID ist über ganze DB, über alle Tabellen, eindeutig, .TABLE ist in der Tabelle eindeutig
    private long id;

    @ManyToOne
    private User user;

    private LocalDateTime erstellungsDatum;

    @Lob
    private String inhalt;

    public Kommentar(){}

    public Kommentar(String inhalt, User user) {
        this.inhalt = inhalt;
        this.user = user;
        this.erstellungsDatum = LocalDateTime.now();
    }

    public String getInhalt() {
        return this.inhalt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getErstellungsDatum() {
        return erstellungsDatum;
    }
}