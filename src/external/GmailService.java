package external;

public class GmailService {
    
    public void sendGmailMessage(String to, String subject, String body) {
        // Simular envío imprimiendo en consola con formato visual
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      GMAIL SERVICE                     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Para    : " + to);
        System.out.println("║ Asunto  : " + subject);
        System.out.println("║ Cuerpo  : " + body);
        System.out.println("║ Estado  : ✓ Email enviado              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
    }
}
