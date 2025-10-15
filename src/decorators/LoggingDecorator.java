package decorators;

import adapters.NotificationSender;
import model.Notification;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// PATRÓN DECORATOR: Registra en consola cada notificación enviada.
// POR QUÉ: Permite añadir logging de forma transversal sin que el notificador original
// o el cliente lo sepan. Es útil para auditoría o depuración.
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
