CREATE SCHEMA IF NOT EXISTS write;

CREATE TABLE IF NOT EXISTS write.users
(
    id   UUID PRIMARY KEY,
    shortname VARCHAR(20) UNIQUE  NOT NULL,
    email     VARCHAR(355) UNIQUE NOT NULL,
    created_at timestamp(6) with time zone not null,
    updated_at timestamp(6) with time zone not null,
    email_verified boolean not null,
    password VARCHAR not null
--     last_login timestamp(6) with time zone not null
);


create unique index IF NOT EXISTS users_email_uindex on write.users (email);

create unique index IF NOT EXISTS users_shortname_uindex on write.users (shortname);


CREATE TABLE IF NOT EXISTS write.roles
(
    id      UUID PRIMARY KEY,
    role    VARCHAR(20) UNIQUE NOT NULL,
    description VARCHAR NOT NULL
);

create unique index IF NOT EXISTS roles_rol_uindex on write.roles (role);


CREATE TABLE IF NOT EXISTS write.user_roles
(
    id_user     UUID not null,
    id_role     UUID not null,
    created_at timestamp(6) with time zone not null,
    constraint user_roles_pk
        primary key (id_user, id_role)
);


