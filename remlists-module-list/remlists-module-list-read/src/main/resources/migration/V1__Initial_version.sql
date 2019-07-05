-- sudo -u postgres createuser genomapp -ldRSP
-- sudo -u genomapp createdb genomappDB


/*CREATE TABLE lists(
                      id UUID  PRIMARY KEY,
                      shortName VARCHAR (20) UNIQUE NOT NULL
--                       created_at timestamp(6) with time zone not null,
--                       updated_at timestamp(6) with time zone not null,
--                       email_verified boolean not null,
--                       last_login timestamp(6) with time zone not null
);*/


CREATE SCHEMA IF NOT EXISTS read;

CREATE TABLE IF NOT EXISTS read.lists
(
    id   UUID PRIMARY KEY,
    shortName VARCHAR(20) UNIQUE NOT NULL
    --                       created_at timestamp(6) with time zone not null,
    --                       updated_at timestamp(6) with time zone not null,
    --                       email_verified boolean not null,
    --                       last_login timestamp(6) with time zone not null
);



CREATE SCHEMA IF NOT EXISTS write;

CREATE TABLE IF NOT EXISTS write.lists
(
    id   UUID PRIMARY KEY,
    shortName VARCHAR(20) UNIQUE NOT NULL
    --                       created_at timestamp(6) with time zone not null,
    --                       updated_at timestamp(6) with time zone not null,
    --                       email_verified boolean not null,
    --                       last_login timestamp(6) with time zone not null
);