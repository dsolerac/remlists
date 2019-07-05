truncate table read.lists;

INSERT INTO read.lists (id, name)
VALUES ('26a5c3c2-51b0-4280-837b-636a8e9fde08', 'AM_Ranga_W');

INSERT INTO read.lists (id, name)
VALUES ('b8b59bd9-03a4-42f1-891a-6195b01903e0', 'AM_dsc_W');

INSERT INTO read.lists (id, name)
VALUES ('add31586-3995-403a-b14e-d5e9f8867231', 'AM_ccm_W');

INSERT INTO read.lists (id, name)
VALUES ('0cef9bdf-ca96-448d-b909-a4b2c388398e', 'AM_psc_W');

--------------------

truncate table write.lists;

INSERT INTO write.lists (id, name)
VALUES ('26a5c3c2-51b0-4280-837b-636a8e9fde08', 'AM_Ranga_R');

INSERT INTO write.lists (id, name)
VALUES ('b8b59bd9-03a4-42f1-891a-6195b01903e0', 'AM_dsc_R');

INSERT INTO write.lists (id, name)
VALUES ('add31586-3995-403a-b14e-d5e9f8867231', 'AM_ccm_R');

INSERT INTO write.lists (id, name)
VALUES ('0cef9bdf-ca96-448d-b909-a4b2c388398e', 'AM_psc_R');
