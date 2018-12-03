package de.awacademy.team_beans.version;

import de.awacademy.team_beans.beitrag.Beitrag;
import de.awacademy.team_beans.beitrag.BeitragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VersionController {

    @Autowired
    VersionsRepository versionsRepository;

    @Autowired
    BeitragRepository beitragRepository;

    @GetMapping("/VersionHistory")
    public String zeigeVersionen(Model model, @RequestParam("currentBeitrag") long currentBeitrag) {

        Beitrag dieserBeitrag = beitragRepository.findById(currentBeitrag).get();

        List<Version> versionList = versionsRepository.findAllByOrderByAenderungsDatumDesc();

        List<Version> versionListForBeitrag = new ArrayList<>();

        for (Version version : versionList) {

            if (version.getBeitrag().equals(dieserBeitrag)) {
                versionListForBeitrag.add(version);
            }
        }

        model.addAttribute("versionen", versionListForBeitrag);

        return "show_versions";
    }
}
