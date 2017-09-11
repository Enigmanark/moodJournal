package journal;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Entry {
    @Id
    public String id;

    private String title;
    private String authorId;
    private String content;

    private List<Moods> moods;

    public List<Moods> getMoods() {
        return moods;
    }

    public void setMoods(List<Moods> moods) {
        this.moods = moods;
    }

    public void addMood(Moods mood) {
        moods.add(mood);
    }

    public boolean hasMood(Moods m) {
        for(Moods mood : moods) {
            if(mood == m) {
                return true;
            }
        }
        return false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorId(String id) {
        authorId = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
