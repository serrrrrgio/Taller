package adapters;

import external.FirebasePush;
import model.Notification;


public class PushAdapter implements NotificationSender {
    
    private final FirebasePush firebaseService;
    
    public PushAdapter() {
        this.firebaseService = new FirebasePush();
    }
    
    public PushAdapter(FirebasePush firebaseService) {
        this.firebaseService = firebaseService;
    }
    
    @Override
    public void send(Notification notification) {
        // Validaciones
        if (notification == null) {
            throw new IllegalArgumentException("La notificación no puede ser nula");
        }
        
        String deviceToken = notification.getRecipient();
        if (deviceToken == null || deviceToken.trim().isEmpty()) {
            throw new IllegalArgumentException("El device token no puede estar vacío");
        }
        
        if (deviceToken.length() < 20) {
            throw new IllegalArgumentException("Device token inválido (muy corto)");
        }
        
        String content = notification.getContent();
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        

        try {
            firebaseService.pushNotification(
                deviceToken,           // deviceToken
                "Nueva Notificación",  // title
                content                // message
            );
            System.out.println("[PushAdapter] ✓ Notificación adaptada y enviada");
        } catch (Exception e) {
            System.err.println("[PushAdapter] ✗ Error: " + e.getMessage());
            throw new RuntimeException("Error al enviar push notification", e);
        }
    }
}
