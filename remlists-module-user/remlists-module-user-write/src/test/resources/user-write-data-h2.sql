-- USERS
truncate table write.users;

INSERT INTO write.users (id, email, shortname, created_at, updated_at, email_verified, password)
VALUES ('26a5c3c2-51b0-4280-837b-636a8e9fde08', 'Ranga@gm.com', 'Ranga', '2017-07-12 05:54:40.44+02', '2017-07-12 05:54:40.44+02', true, '$2y$12$4Ka0hjigu0hlwEa5Oylq2uNyA3xvWvw8nSUcBPRnC3YfeckUyHa22');

INSERT INTO write.users (id, email, shortname, created_at, updated_at, email_verified, password)
VALUES ('b8b59bd9-03a4-42f1-891a-6195b01903e0', 'dsc@gm.com', 'dsc', '2017-07-12 05:54:40.44+02', '2017-07-12 05:54:40.44+02', false, '$2y$12$4Ka0hjigu0hlwEa5Oylq2uNyA3xvWvw8nSUcBPRnC3YfeckUyHa22');

INSERT INTO write.users (id, email, shortname, created_at, updated_at, email_verified, password)
VALUES ('add31586-3995-403a-b14e-d5e9f8867231', 'ccm@gm.com', 'ccm', '2017-07-12 05:54:40.44+02', '2017-07-12 05:54:40.44+02', true, '$2y$12$4Ka0hjigu0hlwEa5Oylq2uNyA3xvWvw8nSUcBPRnC3YfeckUyHa22');

INSERT INTO write.users (id, email, shortname, created_at, updated_at, email_verified, password)
VALUES ('0cef9bdf-ca96-448d-b909-a4b2c388398e', 'psc@gm.com', 'psc', '2017-07-12 05:54:40.44+02', '2017-07-12 05:54:40.44+02', false, '$2y$12$4Ka0hjigu0hlwEa5Oylq2uNyA3xvWvw8nSUcBPRnC3YfeckUyHa22');

-- ROLES
truncate table write.roles;

INSERT INTO write.roles (id, role, description) VALUES ('0782b374-1f4d-4996-9c82-757c583bc48c','USER', 'Generic user');
INSERT INTO write.roles (id, role, description) VALUES ('a7320381-56c8-4453-8a9d-f4c0e9564908','USER_PREMIUM', 'Premium user');
INSERT INTO write.roles (id, role, description) VALUES ('32d2a04f-6b53-4127-ae0c-6f8d582e97bc','USER_FREE', 'Free user');
INSERT INTO write.roles (id, role, description) VALUES ('6fb06c31-7edd-4dd9-8c8a-5ab7bd1631c5','AMDIN', 'Admin user');

-- USER_ROLES
truncate table write.user_roles;

INSERT INTO write.user_roles (id_user, id_role, created_at)
VALUES ('26a5c3c2-51b0-4280-837b-636a8e9fde08', '0782b374-1f4d-4996-9c82-757c583bc48c', '2017-07-12 05:54:40.44+02');
INSERT INTO write.user_roles (id_user, id_role, created_at)
VALUES ('b8b59bd9-03a4-42f1-891a-6195b01903e0', '0782b374-1f4d-4996-9c82-757c583bc48c', '2017-07-12 05:54:40.44+02');
INSERT INTO write.user_roles (id_user, id_role, created_at)
VALUES ('add31586-3995-403a-b14e-d5e9f8867231', '0782b374-1f4d-4996-9c82-757c583bc48c', '2017-07-12 05:54:40.44+02');
INSERT INTO write.user_roles (id_user, id_role, created_at)
VALUES ('0cef9bdf-ca96-448d-b909-a4b2c388398e', '0782b374-1f4d-4996-9c82-757c583bc48c', '2017-07-12 05:54:40.44+02');

INSERT INTO write.user_roles (id_user, id_role, created_at)
VALUES ('0cef9bdf-ca96-448d-b909-a4b2c388398e', 'a7320381-56c8-4453-8a9d-f4c0e9564908', '2017-07-12 05:54:40.44+02');
INSERT INTO write.user_roles (id_user, id_role, created_at)
VALUES ('add31586-3995-403a-b14e-d5e9f8867231', '6fb06c31-7edd-4dd9-8c8a-5ab7bd1631c5', '2017-07-12 05:54:40.44+02');
