package facade;

import adapters.*;
import decorators.*;
import model.Notification;
import model.NotificationType;
import model.Priority;

// PATRÓN FACADE: Proporciona una interfaz unificada y simplificada para un subsistema complejo.
// POR QUÉ: Oculta la complejidad de crear y componer adaptadores y decoradores. El cliente no necesita
// saber cómo instanciar un EmailAdapter, envolverlo en un PriorityDecorator, etc. Simplemente
// crea un objeto `Notification` y llama a un método de la fachada, haciendo el código más limpio.
public class NotificationFacade {
    private int notificationCount = 0;

    public void sendSimpleNotification(Notification notification) {
        NotificationSender sender = createSender(notification.getType());
        send(sender, notification);
    }

    public void sendPriorityNotification(Notification notification, Priority priority) {
        NotificationSender baseSender = createSender(notification.getType());
        NotificationSender prioritySender = new PriorityDecorator(baseSender, priority);
        send(prioritySender, notification);
    }

    public void sendSecureNotification(Notification notification) {
        NotificationSender baseSender = createSender(notification.getType());
        NotificationSender secureSender = new EncryptionDecorator(baseSender);
        send(secureSender, notification);
    }

    public void sendFullNotification(Notification notification, boolean encrypted, boolean withTimestamp, Priority priority) {
        NotificationSender sender = createSender(notification.getType());

        if (priority != null) {
            sender = new PriorityDecorator(sender, priority);
        }
        if (withTimestamp) {
            sender = new TimestampDecorator(sender);
        }
        if (encrypted) {
            sender = new EncryptionDecorator(sender);
        }

        send(sender, notification);
    }

    private NotificationSender createSender(NotificationType type) {
        switch (type) {
            case SMS:
                return new SMSAdapter();
            case PUSH:
                return new PushAdapter();
            case EMAIL:
            default:
                return new EmailAdapter();
        }
    }

    private void send(NotificationSender sender, Notification notification) {
        // Siempre aplicamos el logging para mantener un registro de auditoría.
        NotificationSender finalSender = new LoggingDecorator(sender);
        finalSender.send(notification);
        notificationCount++;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    @Override
    public String toString() {
        return "NotificationFacade [Notificaciones Enviadas: " + notificationCount + "]";
    }
}
