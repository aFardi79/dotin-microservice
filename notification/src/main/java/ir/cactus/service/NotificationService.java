package ir.cactus.service;


import ir.cactus.model.Notification;
import ir.cactus.model.dto.NotificationDTO;
import ir.cactus.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;



    public void createNotification(NotificationDTO notification) {
        notificationRepository.save(toEntity(notification));
    }


    private Notification toEntity(NotificationDTO notification) {
        Notification notification1=new Notification();
        notification1.setEntityID(notification.getEntityID());
        notification1.setEntityName(notification.getEntityName());
        notification1.setMessage(notification.getMessage());
        notification1.setSender(notification.getSender());
        notification1.setTime(notification.getTime());
        return notification1;
    }
}
