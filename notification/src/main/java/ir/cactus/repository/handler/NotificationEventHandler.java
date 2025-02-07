package ir.cactus.repository.handler;

import ir.cactus.model.Notification;
import ir.cactus.repository.NotificationRepository;
import ir.cactus.shared.notification.events.CreateNotificationEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventHandler {

    private final NotificationRepository notificationRepository;

    public NotificationEventHandler(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @EventHandler
    public void on(CreateNotificationEvent event){
        Notification notification = new Notification();
        notification.setId(event.id());
        notification.setMessage(event.message());
        notification.setSender(event.sender());
        notification.setTime(event.time());
        notification.setEntityID(event.entityId());
        notification.setEntityName(event.entityName());
        notificationRepository.save(notification);
    }


}
