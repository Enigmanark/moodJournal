package journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    EntryRepo entryRepo;

    @Autowired
    UserRepo userRepo;

    /*
        Display all journal entries
     */
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

    /*
        Send the form for submitting a new Entry
     */
    @GetMapping("/user/journal/entry/new")
    public String newPost(Model model) {
        model.addAttribute("entry", new Entry());
        return "newEntry";
    }

    /*
        Process a newly submitted Entry
     */
    @PostMapping("/user/journal/entry/new")
    public String newPost(@ModelAttribute Entry entry,
                          @RequestParam(value = "mood_content", defaultValue = "false") String content,
                          @RequestParam(value = "happy", defaultValue = "false") String happy,
                          @RequestParam(value = "excited", defaultValue = "false") String excited,
                          @RequestParam(value = "powerful", defaultValue = "false") String powerful,
                          @RequestParam(value = "giddy", defaultValue = "false") String giddy,

                          @RequestParam(value = "neutral", defaultValue = "false") String neutral,
                          @RequestParam(value = "calm", defaultValue = "false") String calm,
                          @RequestParam(value = "apathetic", defaultValue = "false") String apathetic,
                          @RequestParam(value = "anxious", defaultValue = "false") String anxious,

                          @RequestParam(value = "angry", defaultValue = "false") String angry,
                          @RequestParam(value = "murderous", defaultValue = "false") String murderous,

                          @RequestParam(value = "melancholic", defaultValue = "false") String melancholic,
                          @RequestParam(value = "sad", defaultValue = "false") String sad,
                          @RequestParam(value = "depressed", defaultValue = "false") String depressed,

                          @RequestParam(value = "nervous", defaultValue = "false") String nervous,
                          @RequestParam(value = "worried", defaultValue = "false") String worried,
                          @RequestParam(value = "disappointed", defaultValue = "false") String disappointed,
                          @RequestParam(value = "helpless", defaultValue = "false") String helpless,
                          Model model) throws Exception {
        try {
            ArrayList<Moods> moods = new ArrayList<Moods>();
            if(content.equals("true")) moods.add(Moods.CONTENT);
            if(happy.equals("true")) moods.add(Moods.HAPPY);
            if(excited.equals("true")) moods.add(Moods.EXCITED);
            if(powerful.equals("true")) moods.add(Moods.POWERFUL);
            if(giddy.equals("true")) moods.add(Moods.GIDDY);

            if(neutral.equals("true")) moods.add(Moods.NEUTRAL);
            if(calm.equals("true")) moods.add(Moods.CALM);
            if(apathetic.equals("true")) moods.add(Moods.APATHETIC);
            if(anxious.equals("true")) moods.add(Moods.ANXIOUS);

            if(angry.equals("true")) moods.add(Moods.ANGRY);
            if(murderous.equals("true")) moods.add(Moods.MURDEROUS);

            if(melancholic.equals("true")) moods.add(Moods.MELANCHOLIC);
            if(sad.equals("true")) moods.add(Moods.SAD);
            if(depressed.equals("true")) moods.add(Moods.DEPRESSED);

            if(nervous.equals("true")) moods.add(Moods.NERVOUS);
            if(worried.equals("true")) moods.add(Moods.WORRIED);
            if(disappointed.equals("true")) moods.add(Moods.DISAPPOINTED);
            if(helpless.equals("true")) moods.add(Moods.HELPLESS);

            entry.setMoods(moods);

            entry.setAuthorId(getAuthorId());
            entryRepo.save(entry);
            String id = entry.id;
            return "redirect:/user/journal/entry?id=" + id;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        Display a single journal entry
     */
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

    /*
        Used to retrieve the logged in users author ID for getting this authors journal entries
     */
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
