package com.study.platform.controller.push;

import com.study.interaction.dto.notification.PushNotificationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(path = "/api/v1/notification")
public class NotificationController {

    @GetMapping
    public ResponseEntity<?> sendNotification(@RequestBody PushNotificationDto notificationDto) {

        return ResponseEntity.ok("message sending...");
    }

    @PatchMapping(path = "/{uuid}")
    public ResponseEntity<?> updateStatus(@PathVariable("uuid")UUID uuid) {
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok("deleted");
    }
}
