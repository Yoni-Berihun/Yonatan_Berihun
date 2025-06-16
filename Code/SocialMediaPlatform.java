package Code;

import Code.enums.PostType;
import Code.enums.Visibility;
import Code.enums.NotificationType;
import Code.enums.PrivacySetting;

import java.util.*;

public class SocialMediaPlatform {
    private final Map<String, User> users = new HashMap<>();
    private final List<Post> allPosts = new ArrayList<>();
    private User currentUser;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("+-------------------------------------+");
            System.out.println("| ===     Welcome to YO-SOCIAL    === |");
            System.out.println("+-------------------------------------+");
            System.out.println("| 1. Register                         |");
            System.out.println("| 2. Login                            |");
            System.out.println("| 3. Exit                             |");
            System.out.println("+-------------------------------------+");
            System.out.print("   Enter your choice: ");

            int choice = getUserChoice(3);

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }

    private void registerUser() {
        System.out.println("\n=== User Registration ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            System.out.println("Email cannot be empty.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        PrivacySetting privacySetting = null;
        while (privacySetting == null) {
            System.out.println("\nChoose profile privacy setting:");
            System.out.println("1. PUBLIC");
            System.out.println("2. PRIVATE");
            System.out.println("3. FRIENDS_ONLY");
            System.out.print("Enter choice (1-3): ");
            int privacyChoice = getUserChoice(3);
            switch (privacyChoice) {
                case 1 -> privacySetting = PrivacySetting.PUBLIC;
                case 2 -> privacySetting = PrivacySetting.PRIVATE;
                case 3 -> privacySetting = PrivacySetting.FRIENDS_ONLY;
                default -> System.out.println("Invalid privacy setting choice. Please try again.");
            }
        }

        if (users.containsKey(email)) {
            System.out.println("User with this email already exists.");
        } else {
            User newUser = new User(name, email, password, privacySetting);
            users.put(email, newUser);
            System.out.println("Registration successful!");
        }
    }

    private void login() {
        System.out.println("\n=== User Login ===");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            userMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void userMenu() {
        while (currentUser != null) {
            System.out.println("\n+-------------------------------+");
            System.out.println("| === User Menu for " + currentUser.getName() + " === |");
            System.out.println("+-------------------------------+");
            System.out.println("| 1. Create Post                |");
            System.out.println("| 2. View My Posts              |");
            System.out.println("| 3. View All Users             |");
            System.out.println("| 4. Follow a User              |");
            System.out.println("| 5. View Followers             |");
            System.out.println("| 6. Like a Post                |");
            System.out.println("| 7. Comment on a Post          |");
            System.out.println("| 8. View Statistics            |");
            System.out.println("| 9. View Notifications         |");
            System.out.println("| 10. Logout                    |");
            System.out.println("+-------------------------------+");
            System.out.print("   Enter your choice: ");

            int choice = getUserChoice(10);

            switch (choice) {
                case 1 -> createPost();
                case 2 -> viewMyPosts();
                case 3 -> listUsers();
                case 4 -> followUser();
                case 5 -> viewFollowers();
                case 6 -> likePost();
                case 7 -> commentOnPost();
                case 8 -> viewUserStatistics();
                case 9 -> viewNotifications();
                case 10 -> {
                    currentUser = null;
                    System.out.println("Logged out.");
                }
                default -> System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }

    private void createPost() {
        System.out.println("\n=== Create New Post ===");
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();
        if (content.trim().isEmpty()) {
            System.out.println("Post content cannot be empty.");
            return;
        }

        PostType type = null;
        while (type == null) {
            System.out.println("\nChoose post type:");
            System.out.println("1. TEXT");
            System.out.println("2. IMAGE");
            System.out.println("3. VIDEO");
            System.out.print("Enter type (1-3): ");
            int typeChoice = getUserChoice(3);
            switch (typeChoice) {
                case 1 -> type = PostType.TEXT;
                case 2 -> type = PostType.IMAGE;
                case 3 -> type = PostType.VIDEO;
                default -> System.out.println("Invalid post type choice. Please try again.");
            }
        }

        Visibility visibility = null;
        while (visibility == null) {
            System.out.println("\nChoose visibility:");
            System.out.println("1. PUBLIC");
            System.out.println("2. PRIVATE");
            System.out.println("3. FOLLOWERS_ONLY");
            System.out.print("Enter visibility (1-3): ");
            int visibilityChoice = getUserChoice(3);
            switch (visibilityChoice) {
                case 1 -> visibility = Visibility.PUBLIC;
                case 2 -> visibility = Visibility.PRIVATE;
                case 3 -> visibility = Visibility.FOLLOWERS_ONLY;
                default -> System.out.println("Invalid visibility choice. Please try again.");
            }
        }

        Post post = currentUser.createPost(content, type, visibility);
        allPosts.add(post);
        System.out.println("Post created successfully. Post ID: " + post.getPostId());
    }

    private void viewMyPosts() {
        System.out.println("\n=== My Posts ===");
        if (currentUser.getPosts().isEmpty()) {
            System.out.println("No posts to display yet.");
        } else {
            System.out.println("Displaying posts by " + currentUser.getName() + ":");
            for (Post post : currentUser.getPosts()) {
                System.out.println("---");
                System.out.println("Post ID: " + post.getPostId());
                System.out.println("Content: " + post.getContent());
                System.out.println("Type: " + post.getType());
                System.out.println("Visibility: " + post.getVisibility());
                System.out.println("Likes: " + post.getLikes().size());
                System.out.println("Comments: " + post.getComments().size());
                if (!post.getComments().isEmpty()) {
                    System.out.println("  Comments:");
                    for (Comment comment : post.getComments()) {
                        System.out.println("    " + comment);
                    }
                }
                if (!post.getLikes().isEmpty()) {
                    System.out.println("  Liked by:");
                    for (Like like : post.getLikes()) {
                        System.out.println("    " + like.getUser().getName());
                    }
                }
                System.out.println("---");
            }
        }
    }

    private void listUsers() {
        System.out.println("\n=== All Users ===");
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
        } else {
            System.out.println("Registered Users:");
            for (User user : users.values()) {
                if (canViewUser(user)) {
                    System.out.println("- " + user.getName() + " (" + user.getEmail() + ")");
                }
            }
        }
    }

    private void viewFollowers() {
        System.out.println("\n=== Followers ===");
        if (currentUser.getFollowers().isEmpty()) {
            System.out.println("You have no followers yet.");
        } else {
            System.out.println("You have " + currentUser.getFollowers().size() + " follower(s):");
            for (User follower : currentUser.getFollowers()) {
                System.out.println("- " + follower.getName());
            }
        }
    }

    private void followUser() {
        System.out.println("\n=== Follow a User ===");
        System.out.print("Enter email of user to follow: ");
        String email = scanner.nextLine();

        User userToFollow = users.get(email);

        if (userToFollow != null) {
            if (!canFollowUser(userToFollow)) {
                System.out.println("You cannot follow this user due to their privacy settings.");
                return;
            }
            currentUser.follow(userToFollow);
            if (!currentUser.equals(userToFollow)) {
                Notification notification = new Notification(
                        NotificationType.FOLLOW,
                        currentUser.getName() + " followed you",
                        userToFollow
                );
                userToFollow.addNotification(notification);
            }
        } else {
            System.out.println("User with this email not found.");
        }
    }

    private void likePost() {
        System.out.println("\n=== Like a Post ===");
        System.out.print("Enter post ID to like: ");
        String postId = scanner.nextLine();

        Post postToLike = findPostById(postId);

        if (postToLike != null) {
            if (!canAccessPost(postToLike)) {
                System.out.println("You cannot like this post due to its visibility settings.");
                return;
            }
            boolean alreadyLiked = postToLike.getLikes().stream()
                    .anyMatch(like -> like.getUser().equals(currentUser));
            if (!alreadyLiked) {
                postToLike.addLike(new Like(currentUser, postToLike));
                System.out.println("Post liked.");
                if (!postToLike.getAuthor().equals(currentUser)) {
                    Notification notification = new Notification(
                            NotificationType.LIKE,
                            currentUser.getName() + " liked your post: " + postToLike.getContent(),
                            postToLike.getAuthor()
                    );
                    postToLike.getAuthor().addNotification(notification);
                }
            } else {
                System.out.println("You have already liked this post.");
            }
        } else {
            System.out.println("Post not found with the given ID.");
        }
    }

    private void commentOnPost() {
        System.out.println("\n=== Comment on a Post ===");
        System.out.print("Enter post ID to comment on: ");
        String postId = scanner.nextLine();

        Post postToCommentOn = findPostById(postId);

        if (postToCommentOn != null) {
            if (!canAccessPost(postToCommentOn)) {
                System.out.println("You cannot comment on this post due to its visibility settings.");
                return;
            }
            System.out.print("Enter your comment: ");
            String commentText = scanner.nextLine();
            if (commentText.trim().isEmpty()) {
                System.out.println("Comment cannot be empty.");
                return;
            }
            postToCommentOn.addComment(new Comment(commentText, currentUser, postToCommentOn));
            System.out.println("Comment added.");
            if (!postToCommentOn.getAuthor().equals(currentUser)) {
                Notification notification = new Notification(
                        NotificationType.COMMENT,
                        currentUser.getName() + " commented on your post: " + postToCommentOn.getContent(),
                        postToCommentOn.getAuthor()
                );
                postToCommentOn.getAuthor().addNotification(notification);
            }
        } else {
            System.out.println("Post not found with the given ID.");
        }
    }

    private void viewUserStatistics() {
        if (currentUser == null) {
            System.out.println("No user logged in.");
            return;
        }

        System.out.println("\n=== User Statistics for " + currentUser.getName() + " ===");
        System.out.println("+-------------------+-------+");
        System.out.println("| Statistic         | Count |");
        System.out.println("+-------------------+-------+");
        System.out.printf("| Total Posts       | %-5d |\n", currentUser.getTotalPosts());
        System.out.printf("| Followers         | %-5d |\n", currentUser.getTotalFollowers());
        System.out.printf("| Total Likes       | %-5d |\n", currentUser.getTotalLikes());
        System.out.printf("| Total Comments    | %-5d |\n", currentUser.getTotalComments());
        System.out.printf("| Notifications     | %-5d |\n", currentUser.getNotifications().size());
        System.out.println("+-------------------+-------+");
    }

    private void viewNotifications() {
        System.out.println("\n=== Notifications ===");
        if (currentUser.getNotifications().isEmpty()) {
            System.out.println("No notifications.");
        } else {
            System.out.println("Notifications for " + currentUser.getName() + ":");
            for (Notification notification : currentUser.getNotifications()) {
                System.out.println("- " + notification.getMessage() + " (" + notification.getType() + ", " +
                        (notification.isRead() ? "Read" : "Unread") + ")");
                notification.markAsRead();
            }
        }
    }

    private Post findPostById(String postId) {
        for (Post post : allPosts) {
            if (post.getPostId().equals(postId)) {
                return post;
            }
        }
        return null;
    }

    private boolean canAccessPost(Post post) {
        Visibility visibility = post.getVisibility();
        if (visibility == Visibility.PUBLIC) {
            return true;
        } else if (visibility == Visibility.PRIVATE) {
            return post.getAuthor().equals(currentUser);
        } else if (visibility == Visibility.FOLLOWERS_ONLY) {
            return post.getAuthor().getFollowers().contains(currentUser) || post.getAuthor().equals(currentUser);
        }
        return false;
    }

    private boolean canViewUser(User user) {
        PrivacySetting privacy = user.getPrivacySetting();
        if (privacy == PrivacySetting.PUBLIC) {
            return true;
        } else if (privacy == PrivacySetting.PRIVATE) {
            return user.equals(currentUser);
        } else if (privacy == PrivacySetting.FRIENDS_ONLY) {
            return user.getFollowers().contains(currentUser) || user.equals(currentUser);
        }
        return false;
    }

    private boolean canFollowUser(User user) {
        PrivacySetting privacy = user.getPrivacySetting();
        if (privacy == PrivacySetting.PUBLIC) {
            return true;
        } else if (privacy == PrivacySetting.PRIVATE) {
            return false;
        } else if (privacy == PrivacySetting.FRIENDS_ONLY) {
            return user.getFollowers().contains(currentUser) || user.equals(currentUser);
        }
        return false;
    }

    private int getUserChoice(int maxChoice) {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + maxChoice + ".");
                    System.out.print("Enter your choice: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice: ");
            }
        }
    }
}