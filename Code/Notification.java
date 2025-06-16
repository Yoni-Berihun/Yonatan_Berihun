package Code;

import java.time.LocalDateTime;
import Code.enums.NotificationType;

public class Notification {
    private static int idCounter = 1;
    private final String notificationId;
    private final NotificationType type;
    private final String message;
    private final LocalDateTime timestamp;
    private boolean isRead;
    private final User recipient;

    public Notification(NotificationType type, String message, User recipient) {
        this.notificationId = "N" + (idCounter++);
        this.type = type;
        this.message = message;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    // Getters
    public String getNotificationId() { return notificationId; }
    public NotificationType getType() { return type; }
    public String getMessage() { return message; }
    public boolean isRead() { return isRead; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public User getRecipient() { return recipient; }

    // Actions
    public void markAsRead() { this.isRead = true; }
    public void markAsUnread() { this.isRead = false; }
}