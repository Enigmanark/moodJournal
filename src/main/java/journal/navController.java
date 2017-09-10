package journal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {
    private String title = Application.TITLE + " " + Application.VERSION;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", title);
        return "home";
    }

    @GetMapping("/user/journal")
    public String journal(Model model) {
        model.addAttribute("title", "Journal - " + title);
        return "journal";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact - " + title);
        return "contact";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - " + title);
        return "about";
    }
}
