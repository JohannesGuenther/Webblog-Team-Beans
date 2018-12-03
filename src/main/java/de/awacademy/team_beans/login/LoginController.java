package de.awacademy.team_beans.login;

import de.awacademy.team_beans.session.Session;
import de.awacademy.team_beans.session.SessionRepository;
import de.awacademy.team_beans.user.User;
import de.awacademy.team_beans.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "login";
    }

    @PostMapping("login")
    public String loginSubmit(Model model, LoginDTO login, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findFirstByNameAndPassword(login.getUsername(), login.getPassword());
        // Wenn man Optional benutzt, kann es sein, dass das User-Objekt, dass man sucht, gar nicht vorhanden ist. D.h wir brauchen eine Behandlung für den Fall, dass es das Objekt nicht gibt
        if (optionalUser.isPresent()) {
            Session s = new Session(optionalUser.get());
            sessionRepository.save(s);
            response.addCookie(new Cookie("sessionId", s.getId()));
            return "redirect:/"; // Wenn User-Objekt nicht gefunden werden konnte, dann wird der Nutzer auf das Homeverzeichnis weitergeleitet
        }
        model.addAttribute("login", login);
        return "login";
    }

    @PostMapping("logout")
    public String logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) {
        sessionRepository.deleteById(sessionId);
        Cookie c = new Cookie("sessionId", "");
        c.setMaxAge(0);
        response.addCookie(c);
        return "redirect:/";
    }
}
