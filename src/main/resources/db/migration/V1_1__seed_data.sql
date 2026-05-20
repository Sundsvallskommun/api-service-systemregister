-- ============================================================================
-- Systemregistret — Dev/Test seed data
-- ============================================================================
-- Återskapar det testdata som tidigare seedades av systemregister-backend/src/seed.ts.
-- Körs automatiskt av Flyway vid första uppstart efter att V1_0 applicerats.
--
-- ⚠️  Detta är dev/test-data. För produktion: skapa en separat profil utan
--     denna migration, eller flytta innehållet till en repeatable migration
--     (R__seed_data.sql) som bara körs i dev.
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Organisationer (4)
-- ----------------------------------------------------------------------------

-- Level 1 (root)
INSERT INTO organizations (id, name, description, org_number, email, phone, parent_id, level, created_by) VALUES
  ('org-kommunen', 'Sundsvalls Kommun', 'Huvudorganisation', '212000-2411', 'info@test.domain', '070-000 00 10', NULL, 1, 'seed');

-- Level 2 (departments)
INSERT INTO organizations (id, name, description, org_number, email, phone, parent_id, level, created_by) VALUES
  ('org-it-avd',        'IT-avdelningen',         'Ansvarar för kommunens IT-infrastruktur och system', NULL, 'it@test.domain',          '070-000 00 11', 'org-kommunen', 2, 'seed'),
  ('org-stadsbyggnad',  'Stadsbyggnadskontoret',  'Plan, bygg och mark',                                 NULL, 'stadsbyggnad@test.domain', NULL,           'org-kommunen', 2, 'seed'),
  ('org-socialfv',      'Socialförvaltningen',    'Vård, omsorg och socialtjänst',                       NULL, 'social@test.domain',       NULL,           'org-kommunen', 2, 'seed');

-- ----------------------------------------------------------------------------
-- Leverantörer (3)
-- ----------------------------------------------------------------------------

INSERT INTO suppliers (id, name, org_number, website, contact_email, is_active, created_by) VALUES
  ('sup-techsys',      'TechSys AB',            '556123-4567', 'https://techsys.se',     'support@test.domain',    1, 'seed'),
  ('sup-cloudnordic',  'CloudNordic Solutions', '556987-6543', 'https://cloudnordic.se', 'info@test.domain',       1, 'seed'),
  ('sup-inera',        'Inera AB',              '556583-2475', 'https://inera.se',       'kundservice@test.domain', 1, 'seed');

-- ----------------------------------------------------------------------------
-- Kritikalitetsnivåer (4)
-- ----------------------------------------------------------------------------

INSERT INTO criticality_levels (id, name, code, level, color, is_active, created_by) VALUES
  ('crit-p1', 'Kritisk', 'P1', 4, '#DC2626', 1, 'seed'),
  ('crit-p2', 'Hög',     'P2', 3, '#EA580C', 1, 'seed'),
  ('crit-p3', 'Medium',  'P3', 2, '#CA8A04', 1, 'seed'),
  ('crit-p4', 'Låg',     'P4', 1, '#16A34A', 1, 'seed');

-- ----------------------------------------------------------------------------
-- Personer (4)
-- ----------------------------------------------------------------------------

INSERT INTO persons (id, first_name, last_name, email, phone, title, username, organization_id, created_by) VALUES
  ('per-anna',  'Anna',  'Andersson',     'anna.andersson@test.domain',     '070-000 00 01', 'IT-chef',                'anna.andersson',     'org-it-avd',       'seed'),
  ('per-bertil',  'Bertil',  'Bengtsson', 'bertil.bengtsson@test.domain', '070-000 00 02', 'Systemansvarig',         'bertil.bengtsson', 'org-it-avd',       'seed'),
  ('per-cecilia',   'Cecilia',   'Carlsson',      'cecilia.carlsson@test.domain',       '070-000 00 03', 'Dataskyddsombud (DPO)',  'cecilia.carlsson',       'org-kommunen',     'seed'),
  ('per-david', 'David', 'Davidsson',     'david.davidsson@test.domain',    NULL,           'Handläggare',            'david.davidsson',    'org-stadsbyggnad', 'seed');

-- ----------------------------------------------------------------------------
-- IT-system (4)
-- ----------------------------------------------------------------------------

INSERT INTO systems (id, system_id, name, description, status, version, hosting_type, konfidentialitet, riktighet, tillganglighet, owner_organization_id, system_owner_id, technical_contact_id, supplier_id, criticality_level_id, created_by) VALUES
  ('sys-001', 'SYS-001', 'Ekonomisystemet Raindance',       'Kommunens centrala ekonomisystem för redovisning, budget och fakturahantering.', 'PRODUCTION', '8.4.2',           'INTERNAL', 3, 4, 4, 'org-kommunen',     'per-anna',   'per-bertil', 'sup-techsys',     'crit-p1', 'seed'),
  ('sys-002', 'SYS-002', 'Bygglovsportalen ByggR',          'Handläggningssystem för bygglov, anmälan och tillsyn.',                          'PRODUCTION', '2023.4',          'CLOUD',    2, 3, 3, 'org-stadsbyggnad', 'per-david',  'per-bertil', 'sup-cloudnordic', 'crit-p2', 'seed'),
  ('sys-003', 'SYS-003', 'Socialomsorgssystemet Treserva',  'Verksamhetssystem för socialtjänst, LSS och äldreomsorg.',                       'PRODUCTION', '6.1',             'INTERNAL', 4, 4, 3, 'org-socialfv',     'per-anna',   'per-bertil', 'sup-techsys',     'crit-p1', 'seed'),
  ('sys-004', 'SYS-004', 'Kommunens Intranät',              'Intern kommunikationsplattform och dokumenthantering.',                          'PRODUCTION', 'SharePoint 2023', 'CLOUD',    1, 2, 2, 'org-it-avd',       'per-anna',   'per-bertil', 'sup-cloudnordic', 'crit-p4', 'seed');

-- ----------------------------------------------------------------------------
-- Tjänster (3)
-- ----------------------------------------------------------------------------

INSERT INTO services (id, service_id, name, service_type, endpoint_url, version, hosting_type, konfidentialitet, riktighet, tillganglighet, owner_organization_id, supplier_id, criticality_level_id, created_by) VALUES
  ('svc-001', 'SVC-001', 'Faktura API',           'API', 'https://api.intern.test.domain/faktura/v2',    '2.1.0', 'INTERNAL', 3, 4, 4, 'org-it-avd', 'sup-techsys', 'crit-p2', 'seed'),
  ('svc-002', 'SVC-002', 'BankID-autentisering',  'API', 'https://appapi2.bankid.com/rp/v6.0',            '6.0',   'CLOUD',    4, 4, 4, 'org-it-avd', 'sup-inera',   'crit-p1', 'seed'),
  ('svc-003', 'SVC-003', 'Folkbokföringsuppslag', 'API', 'https://api.skatteverket.se/folkbokforing/v1',  '1.3',   'CLOUD',    3, 4, 3, 'org-it-avd', 'sup-inera',   'crit-p2', 'seed');

-- ----------------------------------------------------------------------------
-- GDPR — Personuppgiftsbehandlingar (2)
-- ----------------------------------------------------------------------------

INSERT INTO personuppgiftsbehandlingar (id, behandling_id, name, status, purpose_description, legal_basis, processes_sensitive_data, processes_ssn, transfers_to_third_country, dpia_required, controller_organization_id, dpo_id, created_by) VALUES
  ('ppb-001', 'PPB-001', 'Handläggning av bygglovsärenden', 'ACTIVE', 'Behandling av personuppgifter i samband med ansökan och handläggning av bygglov, rivningslov och marklov.',  'RATTSLIG_FORPLIKTELSE', 0, 1, 0, 0, 'org-kommunen', 'per-cecilia', 'seed'),
  ('ppb-002', 'PPB-002', 'Socialtjänst och omsorg',         'ACTIVE', 'Behandling av personuppgifter, inklusive känsliga uppgifter om hälsa, inom socialtjänst och LSS-verksamhet.', 'RATTSLIG_FORPLIKTELSE', 1, 1, 0, 1, 'org-kommunen', 'per-cecilia', 'seed');

-- PPB <-> system kopplingar
INSERT INTO personuppgiftsbehandling_system_links (id, behandling_id, system_id, description, created_by) VALUES
  ('ppbsl-001', 'ppb-001', 'sys-002', 'Primärt handläggningssystem',         'seed'),
  ('ppbsl-002', 'ppb-002', 'sys-003', 'Primärt vård- och omsorgssystem',      'seed');

-- ----------------------------------------------------------------------------
-- AI Act — AI-applikationer (2)
-- ----------------------------------------------------------------------------

INSERT INTO ai_applications (id, ai_application_id, name, status, risk_category, high_risk_area, system_id, owner_organization_id, contact_person_id, registration_status, created_by) VALUES
  ('ai-001', 'AI-001', 'Ärendesorterare – Bygglov',     'ACTIVE', 'LIMITED_RISK', NULL,                 'sys-002', 'org-stadsbyggnad', 'per-david', 'not_required', 'seed'),
  ('ai-002', 'AI-002', 'Behovsbedömning socialtjänst',  'DRAFT',  'HIGH_RISK',    'essential_services', 'sys-003', 'org-socialfv',     'per-anna',  'pending',      'seed');
