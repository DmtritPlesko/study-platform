package com.study.platform.service.push;

import java.util.UUID;

public interface NotificationService {

    String sendNotification();

    String updateStatus(UUID uuid);

    void delete(UUID uuid);
}
