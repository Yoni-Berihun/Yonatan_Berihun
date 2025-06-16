package Code;

import Code.enums.PostType;
import Code.enums.Visibility;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post {
    private final String postId;
    private final User author;
    private final String content;
    private final PostType type;
    private final Visibility visibility;
    private final List<Like> likes = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    public Post(User author, String content, PostType type, Visibility visibility) {
        this.postId = UUID.randomUUID().toString();
        this.author = author;
        this.content = content;
        this.type = type;
        this.visibility = visibility;
    }

    public String getPostId() { // Added getter for postId
        return postId;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public PostType getType() {
        return type;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}