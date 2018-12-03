package de.awacademy.team_beans.beitrag;

import de.awacademy.team_beans.kommentar.Kommentar;
import de.awacademy.team_beans.user.User;
import de.awacademy.team_beans.version.Version;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Beitrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime creationDate;

    @Lob
    private String text;

    private String ueberschrift;

    @ManyToOne
    private User user;
    /*
    Kommentarliste wird aus der Verbindung BeitragService -> Kommentare aus Modelio generiert
     */
    @OneToMany
    @JoinColumn (name = "beitrag_id")
    private List<Kommentar> kommentare = new ArrayList<Kommentar> ();

    public Beitrag(){
    }

    public Beitrag(String ueberschrift, String text, User user){
        this.ueberschrift = ueberschrift;
        this.text = text;
        this.creationDate = LocalDateTime.now();
        this.user = user;
    }


    //Getter&Setter

    public String getText() {
        return text;
    }


    public String getUeberschrift() {
        return ueberschrift;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public User getUser() {
        return user;
    }

    public List<Kommentar> getKommentare() {
        return kommentare;
    }

    public void setKommentare(List<Kommentar> kommentare) {
        this.kommentare = kommentare;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
