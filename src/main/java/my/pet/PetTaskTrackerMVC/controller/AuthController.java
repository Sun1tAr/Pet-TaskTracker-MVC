package my.pet.PetTaskTrackerMVC.controller;

import my.pet.PetTaskTrackerMVC.entity.User;
import my.pet.PetTaskTrackerMVC.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userDto,
                               BindingResult bindingResult) {

        Optional<User> user = userService.createUser(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword())
        );
        if (user.isPresent() && userService.addUser(user.get())) {
            return "redirect:/auth/login?success=true";
        } else {
            return "redirect:/auth/login?error=true";
        }
    }
}

