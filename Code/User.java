package Code;

import Code.enums.PostType;
import Code.enums.Visibility;
import Code.enums.PrivacySetting;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects; // Added to fix "cannot find symbol variable Objects"
import java.util.UUID; // Added to fix "cannot find symbol variable UUID"

public class User {
    private final String userId;
    private String name;
    private String email;
    private String password;
    private PrivacySetting privacySetting;
    private final List<User> followers = new ArrayList<>();
    private final List<Post> posts = new ArrayList<>();
    private final List<Notification> notifications = new ArrayList<>();

    public User(String name, String email, String password, PrivacySetting privacySetting) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.privacySetting = privacySetting;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PrivacySetting getPrivacySetting() {
        return privacySetting;
    }

    public void follow(User user) {
        if (!this.equals(user)) {
            if (!user.followers.contains(this)) {
                user.followers.add(this);
                System.out.println("You are now following " + user.getName());
            } else {
                System.out.println("You are already following " + user.getName());
            }
        } else {
            System.out.println("You cannot follow yourself.");
        }
    }

    public List<User> getFollowers() {
        return followers;
    }

    public Post createPost(String content, PostType type, Visibility visibility) {
        Post post = new Post(this, content, type, visibility);
        posts.add(post);
        return post;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public int getTotalPosts() {
        return posts.size();
    }

    public int getTotalFollowers() {
        return followers.size();
    }

    public int getTotalLikes() {
        int totalLikes = 0;
        for (Post post : posts) {
            totalLikes += post.getLikes().size();
        }
        return totalLikes;
    }

    public int getTotalComments() {
        int totalComments = 0;
        for (Post post : posts) {
            totalComments += post.getComments().size();
        }
        return totalComments;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}