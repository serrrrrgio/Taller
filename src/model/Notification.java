package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase de dominio que encapsula toda la información de una notificación.
 * Este objeto viaja a través de adapters y decorators.
 */
public class Notification {
    private final String recipient;
    private final String content;
    private final NotificationType type;
    private final LocalDateTime createdAt;

    public Notification(String recipient, String content, NotificationType type) {
        this.recipient = Objects.requireNonNull(recipient);
        this.content = Objects.requireNonNull(content);
        this.type = Objects.requireNonNull(type);
        this.createdAt = LocalDateTime.now();
    }

    // Constructor de copia para que los decoradores mantengan la inmutabilidad.
    public Notification(Notification other, String newContent) {
        this.recipient = other.recipient;
        this.type = other.type;
        this.createdAt = other.createdAt;
        this.content = newContent; // Se usa el contenido modificado.
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }
}
