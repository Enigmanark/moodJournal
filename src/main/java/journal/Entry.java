package journal;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Entry implements Comparable<Entry>{
    @Id
    public String id;

    private String title;
    private String authorId;
    private String content;
    private Date dateCreated;

    private List<Moods> moods;

    public void setDateCreated() {
        dateCreated = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

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

    @Override
    public int compareTo(Entry entry) {
        return dateCreated.compareTo(entry.getDateCreated());
    }
}
