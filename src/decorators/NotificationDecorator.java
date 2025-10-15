package decorators;

import adapters.NotificationSender;
import model.Notification;

/**
 * PATRÓN DECORATOR (Clase Base Abstracta): Define la estructura para los decoradores.
 * POR QUÉ: Fuerza a que todos los decoradores tengan un `NotificationSender` envuelto (wrapped)
 * y compartan la misma interfaz, permitiendo anidarlos (ej. Logging(Timestamp(EmailAdapter))).
 */
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
