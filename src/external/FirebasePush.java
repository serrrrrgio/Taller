package external;

// Interfaz incompatible: representa un servicio externo de notificaciones push.
public class FirebasePush {
    public void pushNotification(String deviceToken, String title, String message) {
        if (deviceToken == null || deviceToken.trim().isEmpty()) {
            System.out.println("PUSH-ERROR: Invalid device token.");
            return;
        }
        System.out.println("--- FIREBASE PUSH SERVICE ---");
        System.out.println("Sending push to token: " + deviceToken);
        System.out.println("Title: " + title);
        System.out.println("Message: " + message);
        System.out.println("---------------------------\n");
    }
}
