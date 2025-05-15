CREATE TABLE IF NOT EXISTS login_information (
    user_id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    role VARCHAR(50) NOT NULL CHECK (role_code IN ('STUDENT', 'TEACHER', 'ADMIN')),
    refresh_token_hash VARCHAR(255),
    refresh_token_expiry TIMESTAMP
);


insert into login_information
values
('user','123',true,'STUDENT'),
('teacher','111',true,'TEACHER'),
('root','root',true,'ADMIN');