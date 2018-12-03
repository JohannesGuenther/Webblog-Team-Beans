package de.awacademy.team_beans.kommentar;

import de.awacademy.team_beans.beitrag.Beitrag;
import de.awacademy.team_beans.beitrag.BeitragRepository;
import de.awacademy.team_beans.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class KommentarController {

    /**
     * Der Kommentar-Controller händelt alle Logik, die mit den Kommentaren zu tun hat. Schreiben neuer Kommentare und löschen
     */

    @Autowired
    KommentarRepository kommentarRepository;

    @Autowired
    BeitragRepository beitragRepository;

    /**
     *
     * @param model - das Model stellt über das DTO die Verbindung zum View her. Z.B. bekomemn wir vom Model die Information, welcher NUtzer
     *              gerade eingeloggt ist und zu welchem Beitrag ein KOmmentar verfasst wird
     * @param currentBeitrag - long, die als URL-Parameter übergeben wird und die die ID des Beitrags enthält, zu dem ein Kommentar geschrieben wird
     * @return - wenn /kommentar in der URL aufgerifen wird, wird die Seite create_kommentar aufgerufen, wo der KOmmentar geschrieben werden kann
     */

    @GetMapping("/kommentar")
    public String schreibeKommentar(Model model, @RequestParam("currentBeitrag") long currentBeitrag) {
        model.addAttribute("kommentar", new KommentarDTO());
        Beitrag dieserBeitrag = beitragRepository.findById(currentBeitrag).get();
        model.addAttribute("currentBeitrag", dieserBeitrag);
        return "create_kommentar";
    }

    /**
     * schreibeKommentar-Methode beschreibt, was passiert, wenn ein neuer Kommentar erstellt wird.
     * @param kommentar - der vom Nutzer erstellt Kommentar (eingegebener Text), kommt vom DTO
     * @param bindingResult - zur Validierung von Input
     * @param currentUser - der User, der im Moment eingeloggt ist und den Kommentar geschrieben hat
     * @param model - gibt Infos aus dem View an die Methode weiter. Z.B. zu welchem Beitrag ein Kommentar erstellt wurde
     * @param currentBeitrag - die ID des Beitrags, zu dem ein Kommentar erstellt wurde. Wird als URL-Parameter übergeben
     * @return nach erfolgreicher Erstellung des KOmmentars wird der Nutzer auf die Startseite weitergeleitet, ansonsten bleibt er
     * auf der Seite zum Erstellen der Kommentare ("create_kommentar")
     */

    @PostMapping("/kommentar")
    public String schreibeKommentar(@ModelAttribute("kommentar") @Valid KommentarDTO kommentar, BindingResult bindingResult,
                                    @ModelAttribute("currentUser") User currentUser, Model model,
                                    @RequestParam("currentBeitrag") long currentBeitrag) {
        Beitrag dieserBeitrag = beitragRepository.findById(currentBeitrag).get();
        model.addAttribute("currentBeitrag", dieserBeitrag);
        if (bindingResult.hasErrors()) {
            return "create_kommentar";
        }
        Kommentar kommentarEntity = new Kommentar(kommentar.getInhalt(), currentUser);
        kommentarRepository.save(kommentarEntity);
        dieserBeitrag.getKommentare().add(kommentarEntity);
        beitragRepository.save(dieserBeitrag);
        kommentarRepository.save(kommentarEntity);

        return "redirect:/";
    }

    /**
     * löscheKOmmentar händelt, was passiert, wenn man auf das Löschen-Symbol neben einem Kommentar steht, den man selber
     * geschrieben hat. Als Admin kann ein beliebiger Beitrag gelöscht werden, egal, wer ihn geschrieben hat.
     * @param model - Info aus dem View, welcher KOmmentar gelöscht wurde (Übertragen der Kommentar-ID)
     * @param currentBeitrag - ID des Beitrags, dessen Kommentar gelöscht werden soll. Da nur der Beitrag seine Kommentare kennt, und
     *                       nicht der Kommentare seinen Beitrag, müssen wir, um an den richtigen Kommentar zu kommen, über den Beitrag gehen
     * @param currentKommentar - ID des Kommentars, der gelöscht werden soll.
     * @return "redirect:/" - Kommentare werden auf der Startseite angezeigt und gelöscht. Nach Löschen eines Kommentars wird die Startseite neu geladen.
     */

    @PostMapping("/löscheKommentar")
    public String löscheKommentar(Model model,@ModelAttribute("currentBeitrag") Beitrag currentBeitrag, @RequestParam("currentKommentar") long currentKommentar){
        Kommentar dieserKommentar = kommentarRepository.findById(currentKommentar).get();
        model.addAttribute("currentKommentar", dieserKommentar);
        kommentarRepository.deleteById(currentKommentar);

        return "redirect:/";
    }

}

