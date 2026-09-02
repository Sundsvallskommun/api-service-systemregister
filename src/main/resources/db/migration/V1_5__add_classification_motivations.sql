ALTER TABLE systems  ADD COLUMN konfidentialitet_motivering TEXT;
ALTER TABLE systems  ADD COLUMN riktighet_motivering TEXT;
ALTER TABLE systems  ADD COLUMN tillganglighet_motivering TEXT;
ALTER TABLE systems  ADD COLUMN samhallsviktigt BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE systems  ADD COLUMN samhallsviktigt_motivering TEXT;
ALTER TABLE systems  ADD COLUMN klassningsdatum DATE;

-- ============================================================================
-- Lägger till data till motiveringsfälten
-- ============================================================================
UPDATE systems SET
                   konfidentialitet_motivering = 'Systemet hanterar ekonomisk information och löneuppgifter som klassas som känsliga enligt kommunens dataklassificeringspolicy.',
                   riktighet_motivering = 'Felaktiga uppgifter i redovisning eller fakturahantering kan få allvarliga ekonomiska och juridiska konsekvenser för kommunen.',
                   tillganglighet_motivering = 'Systemet måste vara tillgängligt för att kommunen ska kunna betala löner och fakturor i tid.',
                   samhallsviktigt = TRUE,
                   samhallsviktigt_motivering = 'Ekonomisystemet är en förutsättning för kommunens grundläggande funktion, inklusive löneutbetalningar och betalning av leverantörer.',
                   klassningsdatum = '2025-06-07'
WHERE id = 'sys-001';

UPDATE systems SET
                   konfidentialitet_motivering = 'Innehåller personuppgifter och fastighetsinformation i bygglovsärenden, men klassas som medelkänslig då stora delar av informationen är offentlig handling.',
                   riktighet_motivering = 'Felaktig handläggningsinformation kan leda till felaktiga beslut i bygglovsärenden med juridiska konsekvenser.',
                   tillganglighet_motivering = 'Systemet behöver vara tillgängligt under kontorstid för ärendehantering, men kortare avbrott är hanterbara.',
                   samhallsviktigt = FALSE,
                   klassningsdatum = '2026-06-07'
WHERE id = 'sys-002';

UPDATE systems SET
                   konfidentialitet_motivering = 'Hanterar känsliga personuppgifter om hälsa inom socialtjänst, LSS och äldreomsorg enligt OSL och GDPR artikel 9.',
                   riktighet_motivering = 'Felaktig information i journaler och vårdplaner kan direkt påverka enskildas hälsa och säkerhet.',
                   tillganglighet_motivering = 'Systemet används dagligen i vård- och omsorgsarbete och driftstopp kan påverka omsorgen om utsatta personer.',
                   samhallsviktigt = TRUE,
                   samhallsviktigt_motivering = 'Klassas som samhällsviktig verksamhet då systemet stödjer vård och omsorg om äldre och personer med funktionsnedsättning.',
                   klassningsdatum = '2026-07-19'
WHERE id = 'sys-003';

UPDATE systems SET
                   konfidentialitet_motivering = 'Innehåller främst intern, icke-känslig kommunikation och dokumentation.',
                   riktighet_motivering = 'Felaktig information i intranätet har begränsad påverkan då det inte används för myndighetsutövning.',
                   tillganglighet_motivering = 'Ett kortare avbrott i intranätet påverkar inte kommunens kärnverksamhet.',
                   samhallsviktigt = FALSE,
                   klassningsdatum = '2026-06-07'
WHERE id = 'sys-004';