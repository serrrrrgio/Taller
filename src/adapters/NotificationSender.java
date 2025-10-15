package adapters;

import model.Notification;


public interface NotificationSender {
    /**
     * Envía una notificación.
     * @param notification El objeto notificación a enviar
     */
    void send(Notification notification);
}
