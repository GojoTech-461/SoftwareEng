package ma.um6p.confsub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Home page. Public: it shows different content for visitors and logged-in users.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        // principal is null when nobody is logged in
        model.addAttribute("currentEmail", principal == null ? null : principal.getName());
        return "home";
    }
}
