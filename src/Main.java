import adapters.EmailAdapter;
import adapters.NotificationSender;
import adapters.SMSAdapter;
import adapters.PushAdapter;
import decorators.EncryptionDecorator;
import decorators.LoggingDecorator;
import decorators.PriorityDecorator;
import decorators.TimestampDecorator;
import facade.NotificationFacade;
import model.Notification;
import model.NotificationType;
import model.Priority;

public class Main {

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("  DEMOSTRACION CON FACADE");
        System.out.println("=============================================\n");

        NotificationFacade facade = new NotificationFacade();

        // Caso 1: Email simple
        System.out.println("--- CASO 1: Email Simple ---");
        Notification simpleEmail = new Notification(
                "usuario@ejemplo.com",
                "Tienes una nueva tarea pendiente",
                NotificationType.EMAIL
        );
        facade.sendSimpleNotification(simpleEmail);

        // Caso 2: SMS con prioridad
        System.out.println("\n--- CASO 2: SMS con Prioridad Alta ---");
        Notification prioritySms = new Notification(
                "+573001234567",
                "Reunion de emergencia en 5 minutos",
                NotificationType.SMS
        );
        facade.sendPriorityNotification(prioritySms, Priority.HIGH);

        // Caso 3: Email con timestamp
        System.out.println("\n--- CASO 3: Email con Timestamp ---");
        Notification timestampEmail = new Notification(
                "gerente@empresa.com",
                "Reporte diario procesado",
                NotificationType.EMAIL
        );
        facade.sendTimestampedNotification(timestampEmail);

        // Caso 4: Push encriptado
        System.out.println("\n--- CASO 4: Push Encriptado ---");
        Notification securePush = new Notification(
                "device-token-abc123def456ghi789jkl012mno345pqr678stu901vwx234yz",
                "Codigo de verificacion: 847362",
                NotificationType.PUSH
        );
        facade.sendSecureNotification(securePush);

        // Caso 5: Notificacion completa
        System.out.println("\n--- CASO 5: Notificacion Completa ---");
        Notification fullNotification = new Notification(
                "ceo@empresa.com",
                "Reporte Trimestral Q4",
                NotificationType.EMAIL
        );
        facade.sendFullNotification(fullNotification, true, true, Priority.HIGH);

        // Caso 6: Diferentes prioridades
        System.out.println("\n--- CASO 6: Diferentes Prioridades ---");
        Notification lowPriority = new Notification(
                "+573009876543",
                "Mantenimiento programado",
                NotificationType.SMS
        );
        Notification mediumPriority = new Notification(
                "+573009876543",
                "Actualizacion disponible",
                NotificationType.SMS
        );
        Notification highPriority = new Notification(
                "+573009876543",
                "Contrasena cambiada",
                NotificationType.SMS
        );

        facade.sendPriorityNotification(lowPriority, Priority.LOW);
        facade.sendPriorityNotification(mediumPriority, Priority.MEDIUM);
        facade.sendPriorityNotification(highPriority, Priority.HIGH);

        // Estadisticas
        System.out.println("\n--- ESTADISTICAS ---");
        facade.printStatistics();


        // USO SIN FACADE
        System.out.println("\n=============================================");
        System.out.println("  DEMOSTRACION SIN FACADE");
        System.out.println("=============================================\n");

        System.out.println("--- Construccion Manual de Decoradores ---");

        Notification manualNotification = new Notification(
                "usuario@manual.com",
                "Mensaje enviado manualmente",
                NotificationType.EMAIL
        );

        NotificationSender manualSender = new EmailAdapter();
        manualSender = new EncryptionDecorator(manualSender);
        manualSender = new TimestampDecorator(manualSender);
        manualSender = new PriorityDecorator(manualSender, Priority.HIGH);
        manualSender = new LoggingDecorator(manualSender);

        manualSender.send(manualNotification);


        // Comparacion
        System.out.println("\n\n--- COMPARACION ---\n");
        System.out.println("Con Facade: 3 lineas de codigo");
        System.out.println("Sin Facade: 7 lineas de codigo");
    }
}