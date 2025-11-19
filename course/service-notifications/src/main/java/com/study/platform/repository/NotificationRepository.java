package com.study.platform.repository;

import com.study.platform.entity.push.PushNotification;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.List;

import java.util.UUID;

public interface NotificationRepository extends CassandraRepository<PushNotification, UUID> {

    List<PushNotification> findByMessage(String message);
}
