package external;

// Interfaz incompatible: representa un servicio externo de envío de emails.
public class GmailService {
    public void sendGmailMessage(String to, String subject, String body) {
        if (to == null || !to.contains("@")) {
            System.out.println("EMAIL-ERROR: Invalid email address.");
            return;
        }
        System.out.println("--- GMAIL SERVICE ---");
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("---------------------\n");
    }
}
