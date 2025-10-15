package decorators;

import adapters.NotificationSender;
import model.Notification;
import java.time.format.DateTimeFormatter;

// PATRÓN DECORATOR: Añade dinámicamente la fecha y hora al contenido de una notificación.
// POR QUÉ: Permite agregar esta funcionalidad a cualquier `NotificationSender` sin alterar su código.
// Crea una nueva instancia de `Notification` para no modificar el objeto original, respetando la inmutabilidad.
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
