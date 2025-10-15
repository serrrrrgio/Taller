package adapters;

import external.TwilioSMS;
import model.Notification;

/**
 * PATRÓN ADAPTER
 * Adapta el objeto Notification a la interfaz de TwilioSMS
 */
public class SMSAdapter implements NotificationSender {
    
    private final TwilioSMS twilioService;
    
    public SMSAdapter() {
        this.twilioService = new TwilioSMS();
    }
    
    public SMSAdapter(TwilioSMS twilioService) {
        this.twilioService = twilioService;
    }
    
    @Override
    public void send(Notification notification) {
        // Validaciones
        if (notification == null) {
            throw new IllegalArgumentException("La notificación no puede ser nula");
        }
        
        String phoneNumber = notification.getRecipient();
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacío");
        }
        
        if (!twilioService.validatePhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException(
                "Número inválido. Formato esperado: +573001234567"
            );
        }
        
        String content = notification.getContent();
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        
        if (content.length() > 160) {
            System.out.println("SMS largo: se enviará como múltiples mensajes");
        }
        

        try {
            twilioService.sendTextMessage(phoneNumber, content);
            System.out.println("[SMSAdapter] ✓ Notificación adaptada y enviada");
        } catch (Exception e) {
            System.err.println("[SMSAdapter] ✗ Error: " + e.getMessage());
            throw new RuntimeException("Error al enviar SMS", e);
        }
    }
}
