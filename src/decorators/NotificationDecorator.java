package decorators;

import adapters.NotificationSender;
import model.Notification;

public abstract class NotificationDecorator implements NotificationSender {
    protected final NotificationSender wrappedSender;

    public NotificationDecorator(NotificationSender wrappedSender) {
        this.wrappedSender = wrappedSender;
    }

    @Override
    public void send(Notification notification) {
        wrappedSender.send(notification);
    }
}
