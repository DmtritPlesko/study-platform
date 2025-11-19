package com.study.platform.entity.push;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;


@Getter
@Setter
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PushNotification {

    @UUID
    java.util.UUID uuid;

    String userId;

    @Column("created_at")
    LocalDate createdAt;

    @Column("status")
    Status status;

    @Column("message")
    String message;
}

@Getter
enum Status {
    SENT("sent"),
    READ("read"),
    PENDING("pending");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Неизвестный статус: " + value);
    }
}