package de.awacademy.team_beans.version;

import de.awacademy.team_beans.beitrag.Beitrag;
import de.awacademy.team_beans.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime aenderungsDatum;

    @Lob
    private String text;

    private String ueberschrift;

    @ManyToOne
    private Beitrag beitrag;

    @ManyToOne
    private User user;

    public Version(){
    }

    public Version(String ueberschrift, String text, User user, Beitrag beitrag){
        this.ueberschrift = ueberschrift;
        this.text = text;
        this.user = user;
        this.aenderungsDatum = LocalDateTime.now();
        this.beitrag = beitrag;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getAenderungsDatum() {
        return aenderungsDatum;
    }

    public String getText() {
        return text;
    }

    public String getUeberschrift() {
        return ueberschrift;
    }

    public Beitrag getBeitrag() {
        return beitrag;
    }

    public User getUser() {
        return user;
    }
}
