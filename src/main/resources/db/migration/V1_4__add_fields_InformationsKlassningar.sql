ALTER TABLE systems  ADD COLUMN konfidentialitet_motivering TEXT;
ALTER TABLE systems  ADD COLUMN riktighet_motivering TEXT;
ALTER TABLE systems  ADD COLUMN tillganglighet_motivering TEXT;
ALTER TABLE systems  ADD COLUMN samhallsviktigt BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE systems  ADD COLUMN samhallsviktigt_motivering TEXT;