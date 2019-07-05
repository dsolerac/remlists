CREATE SCHEMA IF NOT EXISTS write;

CREATE TABLE IF NOT EXISTS write.lists
(
    id   UUID PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
    --                       created_at timestamp(6) with time zone not null,
    --                       updated_at timestamp(6) with time zone not null,
    --                       email_verified boolean not null,
    --                       last_login timestamp(6) with time zone not null
);