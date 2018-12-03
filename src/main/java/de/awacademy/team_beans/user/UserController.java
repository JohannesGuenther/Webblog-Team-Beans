package de.awacademy.team_beans.user;

import de.awacademy.team_beans.beitrag.Beitrag;
import de.awacademy.team_beans.beitrag.BeitragDTO;
import de.awacademy.team_beans.beitrag.BeitragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String create(Model model) {
        model.addAttribute("user", new UserDTO());
        return "administrate_blog";
    }


    @PostMapping("/user")
    public String create (@ModelAttribute("user") @Valid UserDTO user, @ModelAttribute ("currentUser") User currentUser) {

        User userEntity = new User(user.getName());
        userRepository.save(userEntity);
        return "redirect:/";
    }

}
