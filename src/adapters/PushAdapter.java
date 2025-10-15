package adapters;

import external.FirebasePush;
import model.Notification;

// PATRÓN ADAPTER: Adapta el objeto `Notification` a la interfaz de `FirebasePush`.
// POR QUÉ: Unifica la forma de enviar notificaciones push bajo el método `send(notification)`,
// ocultando la firma específica de `pushNotification`.
public class PushAdapter implements NotificationSender {
    private final FirebasePush firebasePush = new FirebasePush();

    @Override
    public void send(Notification notification) {
        if (notification.getRecipient() == null || notification.getRecipient().trim().isEmpty()) {
            System.out.println("PUSH-ERROR: Invalid recipient for Push Notification.");
            return;
        }

        // Asumimos un formato "título;mensaje" para el contenido
        String[] parts = notification.getContent().split(";", 2);
        String title = parts.length > 1 ? parts[0] : "Notification";
        String message = parts.length > 1 ? parts[1] : notification.getContent();

        firebasePush.pushNotification(notification.getRecipient(), title, message);
    }
}
