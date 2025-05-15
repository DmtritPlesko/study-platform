CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT GENERATED AS UUID,
    username varchar (128),
    name_group varchar(64)
);