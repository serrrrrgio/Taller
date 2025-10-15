package external;

public class TwilioSMS {
    
    public void sendTextMessage(String phoneNumber, String text) {
        // Simular envío imprimiendo en consola con formato visual
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      TWILIO SMS SERVICE                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Teléfono: " + phoneNumber);
        System.out.println("║ Mensaje : " + text);
        System.out.println("║ Estado  : ✓ SMS Enviado                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
    }
    
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && 
               phoneNumber.startsWith("+") && 
               phoneNumber.replaceAll("[^0-9]", "").length() >= 10;
    }
}
