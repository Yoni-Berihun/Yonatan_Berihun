package Code;

public class Comment {
    private final String text;
    private final User author;
    private final Post post;

    public Comment(String text, User author, Post post) {
        this.text = text;
        this.author = author;
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public String toString() {
        return author.getName() + ": " + text;
    }
}