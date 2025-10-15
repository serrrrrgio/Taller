package facade;

import adapters.NotificationSender;
import adapters.EmailAdapter;
import adapters.SMSAdapter;
import adapters.PushAdapter;
import decorators.TimestampDecorator;
import decorators.PriorityDecorator;
import decorators.EncryptionDecorator;
import decorators.LoggingDecorator;
import model.Notification;
import model.NotificationType;
import model.Priority;

public class NotificationFacade {

    private int notificationCount;
    private int errorCount;

    public NotificationFacade() {
        this.notificationCount = 0;
        this.errorCount = 0;
    }

    public void sendSimpleNotification(Notification notification) {
        try {
            // Validar la notificación
            validateNotification(notification);

            // Crear el adapter apropiado según el tipo
            NotificationSender sender = createAdapter(notification.getType());

            // Enviar directamente (sin decoradores)
            sender.send(notification);

            // Incrementar contador
            notificationCount++;

            System.out.println("[Facade] ✓ Notificación simple enviada exitosamente");

        } catch (Exception e) {
            errorCount++;
            System.err.println("[Facade] ✗ Error al enviar notificación simple: " + e.getMessage());
            throw e;
        }
    }

    public void sendPriorityNotification(Notification notification, Priority priority) {
        try {
            // Validar
            validateNotification(notification);
            if (priority == null) {
                throw new IllegalArgumentException("La prioridad no puede ser null");
            }

            // Crear adapter base
            NotificationSender sender = createAdapter(notification.getType());

            // DECORAR: Agregar prioridad
            sender = new PriorityDecorator(sender, priority);

            // DECORAR: Agregar logging (siempre para prioridades)
            sender = new LoggingDecorator(sender);

            // Enviar
            sender.send(notification);

            notificationCount++;
            System.out.println("[Facade] ✓ Notificación con prioridad enviada exitosamente");

        } catch (Exception e) {
            errorCount++;
            System.err.println("[Facade] ✗ Error al enviar notificación con prioridad: " + e.getMessage());
            throw e;
        }
    }

    public void sendTimestampedNotification(Notification notification) {
        try {
            validateNotification(notification);

            NotificationSender sender = createAdapter(notification.getType());

            // DECORAR: Agregar timestamp
            sender = new TimestampDecorator(sender);

            // DECORAR: Agregar logging
            sender = new LoggingDecorator(sender);

            sender.send(notification);

            notificationCount++;
            System.out.println("[Facade] ✓ Notificación con timestamp enviada exitosamente");

        } catch (Exception e) {
            errorCount++;
            System.err.println("[Facade] ✗ Error al enviar notificación con timestamp: " + e.getMessage());
            throw e;
        }
    }

    public void sendSecureNotification(Notification notification) {
        try {
            validateNotification(notification);

            NotificationSender sender = createAdapter(notification.getType());

            // DECORAR: Agregar encriptación
            sender = new EncryptionDecorator(sender);

            // DECORAR: Agregar logging (importante para notificaciones seguras)
            sender = new LoggingDecorator(sender);

            sender.send(notification);

            notificationCount++;
            System.out.println("[Facade] ✓ Notificación segura enviada exitosamente");

        } catch (Exception e) {
            errorCount++;
            System.err.println("[Facade] ✗ Error al enviar notificación segura: " + e.getMessage());
            throw e;
        }
    }

    public void sendFullNotification(Notification notification,
                                     boolean encrypted,
                                     boolean withTimestamp,
                                     Priority priority) {
        try {
            validateNotification(notification);

            // Crear adapter base
            NotificationSender sender = createAdapter(notification.getType());

            // El ORDEN es importante:
            // 1. Primero encriptación (modifica el contenido más profundamente)
            // 2. Luego timestamp (se agrega antes del prefijo de prioridad)
            // 3. Luego prioridad (prefijo visible al inicio)
            // 4. Finalmente logging (último, para capturar todo)

            if (encrypted) {
                sender = new EncryptionDecorator(sender);
                System.out.println("[Facade] → Encriptación activada");
            }

            if (withTimestamp) {
                sender = new TimestampDecorator(sender);
                System.out.println("[Facade] → Timestamp activado");
            }

            if (priority != null) {
                sender = new PriorityDecorator(sender, priority);
                System.out.println("[Facade] → Prioridad " + priority + " activada");
            }

            // Siempre agregar logging para notificaciones completas
            sender = new LoggingDecorator(sender);
            System.out.println("[Facade] → Logging activado");

            // Enviar con todos los decoradores aplicados
            sender.send(notification);

            notificationCount++;
            System.out.println("[Facade] ✓ Notificación completa enviada exitosamente");

        } catch (Exception e) {
            errorCount++;
            System.err.println("[Facade] ✗ Error al enviar notificación completa: " + e.getMessage());
            throw e;
        }
    }

    public void sendUrgentNotification(Notification notification) {
        try {
            validateNotification(notification);

            NotificationSender sender = createAdapter(notification.getType());

            // Combinar timestamp + prioridad alta
            sender = new TimestampDecorator(sender);
            sender = new PriorityDecorator(sender, Priority.HIGH);
            sender = new LoggingDecorator(sender);

            sender.send(notification);

            notificationCount++;
            System.out.println("[Facade] ✓ Notificación urgente enviada exitosamente");

        } catch (Exception e) {
            errorCount++;
            System.err.println("[Facade] ✗ Error al enviar notificación urgente: " + e.getMessage());
            throw e;
        }
    }

    private NotificationSender createAdapter(NotificationType type) {
        if (type == null) {
            throw new IllegalArgumentException("El tipo de notificación no puede ser null");
        }

        // Switch para crear el adapter apropiado
        switch (type) {
            case EMAIL:
                return new EmailAdapter();

            case SMS:
                return new SMSAdapter();

            case PUSH:
                return new PushAdapter();

            default:
                throw new IllegalArgumentException("Tipo de notificación no soportado: " + type);
        }
    }

    private void validateNotification(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("La notificación no puede ser null");
        }

        if (notification.getRecipient() == null || notification.getRecipient().trim().isEmpty()) {
            throw new IllegalArgumentException("El destinatario no puede estar vacío");
        }

        if (notification.getContent() == null || notification.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }

        if (notification.getType() == null) {
            throw new IllegalArgumentException("El tipo de notificación no puede ser null");
        }
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void resetCounters() {
        this.notificationCount = 0;
        this.errorCount = 0;
        System.out.println("[Facade] Contadores reseteados");
    }

    public String getStatisticsReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n╔════════════════════════════════════════════╗\n");
        report.append("║     ESTADÍSTICAS DE NOTIFICACIONES        ║\n");
        report.append("╠════════════════════════════════════════════╣\n");
        report.append(String.format("║ Notificaciones enviadas: %-17d║\n", notificationCount));
        report.append(String.format("║ Errores encontrados    : %-17d║\n", errorCount));

        if (notificationCount > 0) {
            double successRate = ((double)(notificationCount) / (notificationCount + errorCount)) * 100;
            report.append(String.format("║ Tasa de éxito          : %.2f%%           ║\n", successRate));
        }

        report.append("╚════════════════════════════════════════════╝\n");
        return report.toString();
    }

    public void printStatistics() {
        System.out.println(getStatisticsReport());
    }
}