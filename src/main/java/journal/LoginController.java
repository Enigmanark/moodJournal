package journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    private String title = Application.TITLE + " " + Application.VERSION;

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login - " + title);
        return "login";
    }

    @GetMapping("/user/new")
    public String newUser(
            @RequestParam(value="registered", required=false, defaultValue="false") String registered,
            Model model) {
        model.addAttribute("title", "Registration - " + title);
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/user/new")
    public String newUser(@ModelAttribute User user, Model model) {
        model.addAttribute("title", "Registration - " + title);
        user.assignAsUser();
        userRepo.save(user);
        return "redirect:/login?registered";
    }
}
