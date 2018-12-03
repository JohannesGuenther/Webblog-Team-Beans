package de.awacademy.team_beans.session;

import de.awacademy.team_beans.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice //Der Controller Advice ist dem Controller vorgeschaltet. Bevor der Controller ausgeführt wird, wird nachgeschaut, was es im Controller Advice für Methoden gibt
public class SessionControllerAdvice {

    @Autowired
    SessionRepository sessionRepository;

    @ModelAttribute("currentUser")
    public User currentUser(@CookieValue(value = "sessionId", defaultValue = "") String sessionId) { //im current User wird der momentane Nutzer zurückgegeben
        if (sessionId.length() > 0) {
            Optional<Session> sess = sessionRepository.findById(sessionId);
            if (sess.isPresent()) {
                return sess.get().getUser();
            }
        }
        return null;
    }
}
