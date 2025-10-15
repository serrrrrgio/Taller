package external;

// Interfaz incompatible: representa un servicio externo de envío de SMS.
public class TwilioSMS {
    public void sendTextMessage(String phoneNumber, String text) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.out.println("SMS-ERROR: Invalid phone number.");
            return;
        }
        System.out.println("--- TWILIO SMS SERVICE ---");
        System.out.println("Sending SMS to: " + phoneNumber);
        System.out.println("Message: " + text);
        System.out.println("------------------------\n");
    }
}
