package de.awacademy.team_beans.administration;

import de.awacademy.team_beans.beitrag.BeitragRepository;
import de.awacademy.team_beans.user.User;
import de.awacademy.team_beans.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministrationController {

    @Autowired
    private UserRepository userRepository;



    /**
     * Dem Model werden User und eine Liste von Admin Benutzer IDs hinzugefügt
     * Für jeden Nutzer wird überprüft, ob er Admin ist. Ist dies der Fall, wird seine ID der Liste von Admin IDs hinzugefügt
     * @param model
     * @return
     */
    @GetMapping("/administration")
    public String administration (Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        AdminDTO adminDTO = new AdminDTO();
        List<Long> admins = new ArrayList<>();
        for (User user : users) {
            if (user.isAdministrator()) {
                admins.add(user.getId());
            }
        }
        adminDTO.setAdminIDs(admins);
        model.addAttribute("admins", adminDTO);

        return "administrate_blog";
    }


    /**
     * Methode bekommt als Parameter die aktuelle AdminDTO.
     * Für die User aus der Datenbank wird überprüft, ob ihre ID auf der Liste der Admins steht, und entsprechend der Wert gesetzt.
     * Danach werden die User mit ihren neuen Werten gespeichert.
     * @param adminDTO
     * @return
     */
    @PostMapping("/administration")
    public String administration (@ModelAttribute("admins") AdminDTO adminDTO) {

        List<Long> admins = adminDTO.getAdminIDs();

        List<User> users = userRepository.findAll();

        for (User userFromList : users) {
            if (admins.contains(userFromList.getId())) {
                userFromList.setAdministrator(true);
            } else {
                userFromList.setAdministrator(false);
            }

            userRepository.save(userFromList);
        }

        return "redirect:/";
    }

}
