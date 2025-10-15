package external;

public class FirebasePush {
    
    public void pushNotification(String deviceToken, String title, String message) {
        // Simular envío imprimiendo en consola con formato visual
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      FIREBASE PUSH SERVICE             ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Token   : " + deviceToken);
        System.out.println("║ Título  : " + title);
        System.out.println("║ Mensaje : " + message);
        System.out.println("║ Estado  : ✓ Push enviado               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
    }
}
