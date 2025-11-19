package com.study.platform.service.push;

import com.study.platform.repository.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;

    @Override
    public String sendNotification() {
        return "";
    }

    @Override
    public String updateStatus(UUID uuid) {
        return "";
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        notificationRepository.deleteById(uuid);
    }
}
