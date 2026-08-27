ALTER TABLE systems ADD COLUMN risk_analysed BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE systems ADD COLUMN risk_analysed_date DATE;

UPDATE systems SET systems.risk_analysed = TRUE,  systems.risk_analysed_date = '2025-06-07' where id = 'sys-001';
UPDATE systems SET systems.risk_analysed = TRUE,  systems.risk_analysed_date = '2026-06-07' where id = 'sys-002';
UPDATE systems SET systems.risk_analysed = TRUE,  systems.risk_analysed_date = '2026-07-19' where id = 'sys-003';
UPDATE systems SET systems.risk_analysed = FALSE, systems.risk_analysed_date = NULL where id = 'sys-004';