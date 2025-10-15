import adapters.EmailAdapter;
import adapters.NotificationSender;
import adapters.SMSAdapter;
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
        System.out.println("DEMOSTRACIÓN USANDO EL PATRÓN FACADE");
        System.out.println("=============================================\n");

        NotificationFacade facade = new NotificationFacade();

        // 1. Enviar email simple
        System.out.println("--- 1. Enviando Email Simple ---");
        Notification simpleEmail = new Notification("usuario@ejemplo.com", "Tarea simple;Esta es una tarea simple.", NotificationType.EMAIL);
        facade.sendSimpleNotification(simpleEmail);

        // 2. Enviar SMS con prioridad alta y timestamp
        System.out.println("--- 2. Enviando SMS con Prioridad Alta y Timestamp ---");
        Notification prioritySms = new Notification("123456789", "¡Reunión importante en 5 minutos!", NotificationType.SMS);
        facade.sendFullNotification(prioritySms, false, true, Priority.HIGH);

        // 3. Enviar notificación push encriptada
        System.out.println("--- 3. Enviando Notificación Push Encriptada ---");
        Notification securePush = new Notification("device-token-123", "Datos de acceso;usuario:contraseña", NotificationType.PUSH);
        facade.sendSecureNotification(securePush);

        // 4. Enviar notificación completa (Email con todo)
        System.out.println("--- 4. Enviando Notificación Completa (Email) ---");
        Notification fullEmail = new Notification(
            "admin@ejemplo.com",
            "Reporte Final;Este es el reporte de fin de mes.",
            NotificationType.EMAIL
        );
        facade.sendFullNotification(fullEmail, true, true, Priority.HIGH);

        // 5. Mostrar estadísticas
        System.out.println("--- 5. Estadísticas ---");
        System.out.println("Total de notificaciones enviadas a través de la fachada: " + facade.getNotificationCount());
        System.out.println(facade);


        System.out.println("\n\n=============================================");
        System.out.println("DEMO SIN FACADE (USO DIRECTO DE COMPONENTES)");
        System.out.println("=============================================");
        System.out.println("POR QUÉ: Este ejemplo muestra la complejidad que el Facade oculta.");
        System.out.println("El cliente necesita conocer y ensamblar manualmente cada componente (Adapter y Decorators).\n");

        // Mismo caso que el punto 2, pero de forma manual
        System.out.println("--- Recreando el caso 2 (SMS con Prioridad y Timestamp) manualmente ---");

        // El cliente debe construir la cadena de responsabilidad manualmente:
        // 1. Crear el notificador base (Adapter)
        NotificationSender manualSmsSender = new SMSAdapter();
        // 2. Envolverlo con el decorador de prioridad
        manualSmsSender = new PriorityDecorator(manualSmsSender, Priority.HIGH);
        // 3. Envolverlo con el decorador de timestamp
        manualSmsSender = new TimestampDecorator(manualSmsSender);
        // 4. Envolverlo con el decorador de logging (que la fachada aplica implícitamente)
        manualSmsSender = new LoggingDecorator(manualSmsSender);

        // 5. Crear el objeto de notificación y enviarlo
        Notification manualSms = new Notification("987654321", "¡Reunión importante en 5 minutos! (manual)", NotificationType.SMS);
        manualSmsSender.send(manualSms);

        System.out.println("\nLíneas de código para enviar la notificación:");
        System.out.println("- Con Facade: 2 (crear Notification, llamar a facade.sendFullNotification)");
        System.out.println("- Manualmente: 5 (crear Notification, instanciar y anidar 3 decoradores y 1 adapter, llamar a send)");
        System.out.println("El Facade es claramente más simple, reduce el código duplicado y desacopla al cliente del subsistema de notificaciones.");
    }
}
