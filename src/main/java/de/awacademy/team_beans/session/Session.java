package de.awacademy.team_beans.session;

import de.awacademy.team_beans.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Session {
    @Id
    private String id;

    @ManyToOne
    private User user;

    public Session() {
    }

    public Session(User user) {
        this.user = user;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
