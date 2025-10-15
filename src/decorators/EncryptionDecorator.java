package decorators;

import adapters.NotificationSender;
import model.Notification;
import java.util.Base64;

public class EncryptionDecorator extends NotificationDecorator {

    public EncryptionDecorator(NotificationSender wrappedSender) {
        super(wrappedSender);
    }

    @Override
    public void send(Notification notification) {
        String originalContent = notification.getContent();
        String encryptedContent = "[ENCRYPTED] " + Base64.getEncoder().encodeToString(originalContent.getBytes());
        Notification decoratedNotification = new Notification(notification, encryptedContent);
        super.send(decoratedNotification);
    }
}
