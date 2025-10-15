package decorators;

import adapters.NotificationSender;
import model.Notification;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LoggingDecorator extends NotificationDecorator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggingDecorator(NotificationSender wrappedSender) {
        super(wrappedSender);
    }

    @Override
    public void send(Notification notification) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[LOG] " + timestamp + " - Enviando a: " + notification.getRecipient() + " (" + notification.getType() + ")");
        // Delega la llamada al siguiente en la cadena sin modificar la notificación.
        super.send(notification);
    }
}
