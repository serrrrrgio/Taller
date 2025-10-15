package adapters;

import external.TwilioSMS;
import model.Notification;

// PATRÓN ADAPTER: Adapta el objeto `Notification` a la interfaz de `TwilioSMS`.
// POR QUÉ: Permite que el sistema use el servicio de Twilio a través de la interfaz común `send`,
// sin necesidad de conocer los detalles de `sendTextMessage`.
public class SMSAdapter implements NotificationSender {
    private final TwilioSMS twilioSMS = new TwilioSMS();

    @Override
    public void send(Notification notification) {
        if (notification.getRecipient() == null || notification.getRecipient().trim().isEmpty()) {
            System.out.println("SMS-ERROR: Invalid recipient for SMS.");
            return;
        }
        twilioSMS.sendTextMessage(notification.getRecipient(), notification.getContent());
    }
}
