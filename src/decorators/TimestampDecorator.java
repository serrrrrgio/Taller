package decorators;

import adapters.NotificationSender;
import model.Notification;
import java.time.format.DateTimeFormatter;


public class TimestampDecorator extends NotificationDecorator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TimestampDecorator(NotificationSender wrappedSender) {
        super(wrappedSender);
    }

    @Override
    public void send(Notification notification) {
        String timestamp = notification.getCreatedAt().format(formatter);
        String newContent = "[" + timestamp + "] " + notification.getContent();

        // Crea una copia de la notificación con el contenido modificado
        Notification decoratedNotification = new Notification(notification, newContent);

        super.send(decoratedNotification);
    }
}
