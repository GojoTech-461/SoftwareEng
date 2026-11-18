package ma.um6p.confsub.controller;

import jakarta.validation.Valid;
import ma.um6p.confsub.dto.RegistrationForm;
import ma.um6p.confsub.service.EmailAlreadyUsedException;
import ma.um6p.confsub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Web layer for login and registration.
 * The POST /login request is handled directly by Spring Security, not by this class.
 */
@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";   // -> templates/login.html
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("form", new RegistrationForm());   // empty form object
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("form") RegistrationForm form,
                           BindingResult result) {

        // Extra check that annotations cannot do alone
        if (form.getPassword() != null && !form.getPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "mismatch", "Passwords do not match");
        }

        if (result.hasErrors()) {
            return "register";   // show the form again with the error messages (NFR-USA-03)
        }

        try {
            userService.register(form);
        } catch (EmailAlreadyUsedException e) {
            result.rejectValue("email", "duplicate", "An account already exists with this email");
            return "register";
        }

        return "redirect:/login?registered";
    }
}
