package decorators;

import adapters.NotificationSender;
import model.Notification;
import model.Priority;

// PATRÓN DECORATOR: Añade una etiqueta de prioridad al mensaje.
// POR QUÉ: Permite marcar notificaciones como importantes dinámicamente. Se puede aplicar a cualquier
// `NotificationSender` y se compone fácilmente con otros decoradores.
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
