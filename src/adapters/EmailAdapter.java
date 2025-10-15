package adapters;

import external.GmailService;
import model.Notification;

/**
 * PATRÓN ADAPTER
 * Adapta el objeto Notification a la interfaz de GmailService
 */
public class EmailAdapter implements NotificationSender {
    
    private GmailService gmailService;
    
    public EmailAdapter() {
        this.gmailService = new GmailService();
    }
    
    public EmailAdapter(GmailService gmailService) {
        this.gmailService = gmailService;
    }
    
    @Override
    public void send(Notification notification) {
        // Validaciones
        if (notification == null) {
            throw new IllegalArgumentException("La notificación no puede ser nula");
        }
        
        String email = notification.getRecipient();
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: debe contener @");
        }
        
        String content = notification.getContent();
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        
        // ADAPTACIÓN: Convertir Notification a los parámetros de GmailService
        try {
            gmailService.sendGmailMessage(
                email,              // to
                "Notificación",     // subject
                content             // body
            );
            System.out.println("[EmailAdapter] ✓ Notificación adaptada y enviada");
        } catch (Exception e) {
            System.err.println("[EmailAdapter] ✗ Error: " + e.getMessage());
            throw new RuntimeException("Error al enviar email", e);
        }
    }
}
