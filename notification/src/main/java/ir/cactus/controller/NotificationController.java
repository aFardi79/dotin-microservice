package ir.cactus.controller;

import ir.cactus.model.Notification;
import ir.cactus.model.dto.NotificationDTO;
import ir.cactus.service.NotificationService;
import ir.cactus.shared.notification.commands.CreateNotificationCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/api/v1/notification")
@Slf4j
public class NotificationController {

    private final  NotificationService notificationService;
    private final CommandGateway commandGateway;

    public NotificationController(NotificationService notificationService, CommandGateway commandGateway) {
        this.notificationService = notificationService;
        this.commandGateway = commandGateway;
    }



    @PostMapping("/createNotification")
    public void createNotification(@RequestBody NotificationDTO notification){
        log.info("Create notification");
        commandGateway.send(new CreateNotificationCommand(RandomGenerator.getDefault().nextLong(), notification.getSender(),notification.getMessage(),notification.getEntityName(),notification.getTime(),notification.getEntityID()));
        log.info("Notification created");
    }
}
