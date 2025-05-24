CREATE TABLE IF NOT EXISTS login_information (
    user_id varchar(128) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    role VARCHAR(50) NOT NULL,
    refresh_token_hash VARCHAR(255),
    refresh_token_expiry TIMESTAMP
);