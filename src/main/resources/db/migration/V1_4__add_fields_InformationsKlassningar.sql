ALTER TABLE informationsklassningar ADD COLUMN konfidentialitet_motivering TEXT;
ALTER TABLE informationsklassningar ADD COLUMN riktighet_motivering TEXT;
ALTER TABLE informationsklassningar ADD COLUMN tillganglighet_motivering TEXT;
ALTER TABLE informationsklassningar ADD COLUMN samhallsviktigt BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE informationsklassningar ADD COLUMN samhallsviktigt_motivering TEXT;

ALTER TABLE informationsklassningar CHANGE COLUMN klassningsdatum klassning_datum DATE;
ALTER TABLE informationsklassningar DROP COLUMN motivering;

