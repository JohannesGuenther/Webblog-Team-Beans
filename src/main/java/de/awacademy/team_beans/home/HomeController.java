package de.awacademy.team_beans.home;

import de.awacademy.team_beans.beitrag.BeitragRepository;
import de.awacademy.team_beans.kommentar.KommentarRepository;
import de.awacademy.team_beans.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BeitragRepository beitragRepository;

    @Autowired
    private KommentarRepository kommentarRepository;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("/") //Wenn ich das Wurzelverzeichnis (/) aufrufe, soll das Model aufgerufen werden
    public String home(Model model) {
        model.addAttribute("beitraege", beitragRepository.findAllByOrderByCreationDateDesc()); //Durch Model werden die Greetings an mein Template weitergegeben
        //.findAll = finde alle Einträge aus meiner Tabelle "greetings"
        model.addAttribute("kommentare", kommentarRepository.findAllByOrderByErstellungsDatum());
        return "home";
    }


}
