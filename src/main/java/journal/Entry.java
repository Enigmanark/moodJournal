package journal;

import org.springframework.data.annotation.Id;

public class Entry {
    @Id
    public String id;

    private String title;
    private String authorId;
    private String content;

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
