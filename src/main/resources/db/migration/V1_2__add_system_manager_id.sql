ALTER TABLE systems ADD COLUMN system_manager_id VARCHAR(255);
ALTER TABLE systems ADD CONSTRAINT fk_systems_manager FOREIGN KEY (system_manager_id) REFERENCES persons(id);

UPDATE systems SET systems.system_manager_id = 'per-david' where id = 'sys-001';
UPDATE systems SET systems.system_manager_id = 'per-david' where id = 'sys-002';
UPDATE systems SET systems.system_manager_id = 'per-anna'  where id = 'sys-003';
UPDATE systems SET systems.system_manager_id = 'per-anna'  where id = 'sys-004';


