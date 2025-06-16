package Code;

public class Like {
    private final User user;
    private final Post post;

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }
}