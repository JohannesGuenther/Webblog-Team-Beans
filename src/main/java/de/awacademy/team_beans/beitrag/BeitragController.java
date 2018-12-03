package de.awacademy.team_beans.beitrag;

import de.awacademy.team_beans.kommentar.Kommentar;
import de.awacademy.team_beans.kommentar.KommentarRepository;
import de.awacademy.team_beans.user.User;
import de.awacademy.team_beans.version.Version;
import de.awacademy.team_beans.version.VersionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BeitragController {

    @Autowired
    BeitragRepository beitragRepository;

    @Autowired
    KommentarRepository kommentarRepository;

    @Autowired
    VersionsRepository versionsRepository;


    @GetMapping("/beitrag")
    public String create(Model model) {
        model.addAttribute("beitrag", new BeitragDTO());
        return "create_beitrag";
    }


    @PostMapping("/beitrag")
    public String create(@ModelAttribute("beitrag") @Valid BeitragDTO beitrag, BindingResult bindingResult,
                         @ModelAttribute("currentUser") User currentUser) {
        if (bindingResult.hasErrors()) {
            return "create_beitrag";
        }

        Beitrag beitragEntity = new Beitrag(beitrag.getUeberschrift(), beitrag.getText(), currentUser);
        beitragRepository.save(beitragEntity);

        Version versionEntity = new Version(beitrag.getUeberschrift(), beitrag.getText(), currentUser, beitragEntity);
        versionsRepository.save(versionEntity);

        return "redirect:/";
    }

    @GetMapping("/BeitragBearbeiten")
    public String bearbeiteBeitrag(Model model, @RequestParam("currentBeitrag") long currentBeitrag) {


        BeitragDTO diesebeitragDTO = new BeitragDTO();

        Beitrag dieserBeitrag = beitragRepository.findById(currentBeitrag).get();

        diesebeitragDTO.setText(dieserBeitrag.getText());
        diesebeitragDTO.setUeberschrift(dieserBeitrag.getUeberschrift());

        model.addAttribute("currentBeitrag", diesebeitragDTO);
        model.addAttribute("beitragId", currentBeitrag);

        return "edit_beitrag";
    }

    @PostMapping("/BeitragBearbeiten")
    public String bearbeiteBeitrag(@ModelAttribute("currentBeitrag") @Valid BeitragDTO beitrag, BindingResult bindingResult,
                                   @RequestParam("currentBeitrag") long currentBeitrag,
                                   @ModelAttribute("currentUser") User currentUser, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("beitragId", currentBeitrag);
            return "edit_beitrag";
        }

        Beitrag dieserBeitrag = beitragRepository.findById(currentBeitrag).get();
        dieserBeitrag.setText(beitrag.getText());
        dieserBeitrag.setUeberschrift(beitrag.getUeberschrift());

        beitragRepository.save(dieserBeitrag);

        Version versionEntity = new Version(beitrag.getUeberschrift(), beitrag.getText(), currentUser, dieserBeitrag);
        versionsRepository.save(versionEntity);

        return "redirect:/";
    }

    @PostMapping("/loescheBeitrag")
    public String loescheBeitrag(@RequestParam("currentBeitragId") long currentBeitragId) {
        Beitrag dieserBeitrag = beitragRepository.findById(currentBeitragId).get();
        List<Kommentar> dieserBeitragKommentare = dieserBeitrag.getKommentare();
        kommentarRepository.deleteAll(dieserBeitragKommentare);
        beitragRepository.deleteById(currentBeitragId);
        return "redirect:/";
    }

}
