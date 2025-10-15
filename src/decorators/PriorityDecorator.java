package decorators;

import adapters.NotificationSender;
import model.Notification;
import model.Priority;

public class PriorityDecorator extends NotificationDecorator {
    private final Priority priority;

    public PriorityDecorator(NotificationSender wrappedSender, Priority priority) {
        super(wrappedSender);
        this.priority = priority;
    }

    @Override
    public void send(Notification notification) {
        String newContent = "[" + priority + "] " + notification.getContent();
        Notification decoratedNotification = new Notification(notification, newContent);
        super.send(decoratedNotification);
    }
}
