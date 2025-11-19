CREATE KEYSPACE IF NOT EXISTS notification_keyspace
WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};

CREATE TABLE IF NOT EXISTS notifications (
    user_id UUID,
    created_at TIMESTAMP,
    notification_id UUID,
    type TEXT,           -- 'EMAIL', 'PUSH', 'SMS'
    status TEXT,         -- 'SENT', 'READ', 'PENDING'
    message TEXT,
    PRIMARY KEY ((user_id), created_at, notification_id)
) WITH CLUSTERING ORDER BY (created_at DESC);