package adapters;

import external.GmailService;
import model.Notification;

// PATRÓN ADAPTER: Traduce un objeto `Notification` a los parámetros que `GmailService` espera.
// POR QUÉ: Permite que el sistema trate a todos los notificadores de forma homogénea a través de la
// interfaz `NotificationSender`, sin importar que el servicio subyacente (Gmail) tenga una firma diferente.
public class EmailAdapter implements NotificationSender {
    private final GmailService gmailService = new GmailService();

    @Override
    public void send(Notification notification) {
        if (notification.getRecipient() == null || !notification.getRecipient().contains("@")) {
            System.out.println("EMAIL-ERROR: Invalid recipient for email.");
            return;
        }

        // Asumimos que el contenido puede tener un formato "asunto;cuerpo"
        String[] parts = notification.getContent().split(";", 2);
        String subject = parts.length > 1 ? parts[0] : "Notification";
        String body = parts.length > 1 ? parts[1] : notification.getContent();

        gmailService.sendGmailMessage(notification.getRecipient(), subject, body);
    }
}
