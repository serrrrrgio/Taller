package adapters;

import model.Notification;

/**
 * Interfaz común para todos los enviadores de notificaciones.
 * Esta interfaz permite usar todos los servicios de forma UNIFORME
 * sin importar sus diferencias internas.
 */
public interface NotificationSender {
    /**
     * Envía una notificación.
     * @param notification El objeto notificación a enviar
     */
    void send(Notification notification);
}
