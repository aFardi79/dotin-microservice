package ir.cactus.controller;

import ir.cactus.model.Notification;
import ir.cactus.model.dto.NotificationDTO;
import ir.cactus.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/createNotification")
    public void createNotification(@RequestBody NotificationDTO notification){
        notificationService.createNotification(notification);
    }
}
