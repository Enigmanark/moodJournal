package journal;

public class Entry {
    private String authorId;
    private String content;

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
