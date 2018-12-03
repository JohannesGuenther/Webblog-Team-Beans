package de.awacademy.team_beans.signup;

import de.awacademy.team_beans.user.User;
import de.awacademy.team_beans.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("signup")
    public String signup(Model model) {
        model.addAttribute("signup", new SignupDTO());
        return "signup";
    }

    @PostMapping("signup")

    public String signup(@ModelAttribute("signup") @Valid SignupDTO signup, BindingResult bindingResult) {
        if (!signup.getPassword1().equals(signup.getPassword2())) {
            bindingResult.addError(new FieldError("signup", "password2", "Passwords do not match"));
        }

        if (userRepository.existsByName(signup.getUsername())) {
            bindingResult.addError(new FieldError("signup", "username", "Username taken"));
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        User user = new User(signup.getUsername(), signup.getPassword1());
        userRepository.save(user);
        return "redirect:/login";
    }
}
