package adapters;

import model.Notification;

/**
 * Interfaz estándar para cualquier objeto capaz de enviar una notificación.
 * Trabaja con un objeto Notification para encapsular los datos.
 */
public interface NotificationSender {
    void send(Notification notification);
}
