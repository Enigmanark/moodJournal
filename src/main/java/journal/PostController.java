package journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    EntryRepo entryRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/user/journal")
    public String posts(Model model) throws Exception {
        try {
            String id = getAuthorId();
            List<Entry> entries = entryRepo.findByAuthorId(id);
            model.addAttribute("entries", entries);
            return "journal";
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/user/journal/entry/new")
    public String newPost(Model model) {
        model.addAttribute("entry", new Entry());
        return "newEntry";
    }

    @PostMapping("/user/journal/entry/new")
    public String newPost(@ModelAttribute Entry entry, Model model) throws Exception {
        try {
            entry.setAuthorId(getAuthorId());
            entryRepo.save(entry);
            String id = entry.id;
            return "redirect:/user/journal/entry?id=" + id;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/user/journal/entry")
    public String entryDetail(@RequestParam(value = "id", required = true)String id, Model model) throws Exception {
        Entry entry = entryRepo.findById(id);
        if(entry == null) {
            throw new Exception("The Post Controller could not find the entry by the id " + id);
        }
        else {
            model.addAttribute("entry", entry);
            return "entryDetail";
        }
    }

    private String getAuthorId() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            throw new Exception("PostController was not able to get username from the authentication system!");
        } else {
            User user = userRepo.findByUsername(username);
            if (user == null) {
                throw new Exception("PostController was not able to find logged in user!");
            } else {
                String id = user.id;
                if (id == null) {
                    throw new Exception("PostController was unable to retrieve user's Id!");
                } else {
                    return id;
                }
            }

        }
    }
}
