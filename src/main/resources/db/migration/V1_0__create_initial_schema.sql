-- ============================================================================
-- Systemregistret — Initial schema
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Code list tables (Integer PKs)
-- ----------------------------------------------------------------------------

CREATE TABLE kod_format (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_bevarande_gallring (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT,
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_gallringsfrist_typ (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT,
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_bevarande_lagstod (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT,
    gallringsfrist_ar INT,
    lagrum VARCHAR(500),
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_registrering (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT,
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_sekretess_lagrum (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT,
    lagrum VARCHAR(500),
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_personuppgift (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT,
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_ansvarstyp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE kod_plan_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    ordning INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- KLASSA 2.1 hierarchy (Integer PKs)
-- ----------------------------------------------------------------------------

CREATE TABLE klassa_verksamhetstyper (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod INT NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    beskrivning TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE klassa_verksamhetsomraden (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL,
    namn VARCHAR(255) NOT NULL,
    verksamhetstyp_id INT NOT NULL,
    aktiv TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT fk_verksamhetsomraden_verksamhetstyp FOREIGN KEY (verksamhetstyp_id) REFERENCES klassa_verksamhetstyper(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE klassa_processgrupper (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL,
    namn VARCHAR(255) NOT NULL,
    verksamhetsomrade_id INT NOT NULL,
    aktiv TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT fk_processgrupper_verksamhetsomrade FOREIGN KEY (verksamhetsomrade_id) REFERENCES klassa_verksamhetsomraden(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE klassa_processer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kod VARCHAR(50) NOT NULL,
    namn VARCHAR(255) NOT NULL,
    beskrivning TEXT,
    processgrupp_id INT NOT NULL,
    aktiv TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT fk_processer_processgrupp FOREIGN KEY (processgrupp_id) REFERENCES klassa_processgrupper(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- Core entities (UUID PKs with audit columns)
-- ----------------------------------------------------------------------------

CREATE TABLE organizations (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    org_number VARCHAR(50),
    email VARCHAR(255),
    phone VARCHAR(50),
    parent_id VARCHAR(255),
    level INT NOT NULL CHECK (level BETWEEN 1 AND 3),
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_organizations_parent FOREIGN KEY (parent_id) REFERENCES organizations(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE persons (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50),
    title VARCHAR(255),
    username VARCHAR(100) UNIQUE,
    organization_id VARCHAR(255),
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_persons_organization FOREIGN KEY (organization_id) REFERENCES organizations(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_persons_username ON persons(username);

CREATE TABLE suppliers (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    org_number VARCHAR(50),
    description TEXT,
    website VARCHAR(500),
    contact_email VARCHAR(255),
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE criticality_levels (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(20) UNIQUE,
    description TEXT,
    level INT NOT NULL,
    color VARCHAR(7),
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE systems (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    system_id VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    version VARCHAR(50),
    documentation_url VARCHAR(500),
    criticality_level_id VARCHAR(255),
    konfidentialitet INT,
    riktighet INT,
    tillganglighet INT,
    owner_organization_id VARCHAR(255),
    system_owner_id VARCHAR(255),
    technical_contact_id VARCHAR(255),
    hosting_type VARCHAR(50),
    supplier_id VARCHAR(255),
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_systems_criticality FOREIGN KEY (criticality_level_id) REFERENCES criticality_levels(id),
    CONSTRAINT fk_systems_owner_org FOREIGN KEY (owner_organization_id) REFERENCES organizations(id),
    CONSTRAINT fk_systems_owner FOREIGN KEY (system_owner_id) REFERENCES persons(id),
    CONSTRAINT fk_systems_tech_contact FOREIGN KEY (technical_contact_id) REFERENCES persons(id),
    CONSTRAINT fk_systems_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE services (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    service_id VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    version VARCHAR(50),
    endpoint_url VARCHAR(500),
    documentation_url VARCHAR(500),
    service_type VARCHAR(50) NOT NULL,
    criticality_level_id VARCHAR(255),
    konfidentialitet INT,
    riktighet INT,
    tillganglighet INT,
    owner_organization_id VARCHAR(255),
    service_owner_id VARCHAR(255),
    technical_contact_id VARCHAR(255),
    hosting_type VARCHAR(50),
    supplier_id VARCHAR(255),
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_services_criticality FOREIGN KEY (criticality_level_id) REFERENCES criticality_levels(id),
    CONSTRAINT fk_services_owner_org FOREIGN KEY (owner_organization_id) REFERENCES organizations(id),
    CONSTRAINT fk_services_owner FOREIGN KEY (service_owner_id) REFERENCES persons(id),
    CONSTRAINT fk_services_tech_contact FOREIGN KEY (technical_contact_id) REFERENCES persons(id),
    CONSTRAINT fk_services_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- Junction / link tables
-- ----------------------------------------------------------------------------

CREATE TABLE system_service_links (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    system_id VARCHAR(255) NOT NULL,
    service_id VARCHAR(255) NOT NULL,
    direction VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ssl_system FOREIGN KEY (system_id) REFERENCES systems(id) ON DELETE CASCADE,
    CONSTRAINT fk_ssl_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE system_dependencies (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    source_system_id VARCHAR(255) NOT NULL,
    target_system_id VARCHAR(255) NOT NULL,
    dependency_type VARCHAR(50) NOT NULL,
    description TEXT,
    is_critical TINYINT(1) NOT NULL DEFAULT 0,
    documentation_url VARCHAR(500),
    contract_file_path VARCHAR(500),
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_sd_source FOREIGN KEY (source_system_id) REFERENCES systems(id) ON DELETE CASCADE,
    CONSTRAINT fk_sd_target FOREIGN KEY (target_system_id) REFERENCES systems(id) ON DELETE CASCADE,
    CONSTRAINT uq_system_dependency UNIQUE (source_system_id, target_system_id, dependency_type),
    CONSTRAINT chk_no_self_dep CHECK (source_system_id != target_system_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- GDPR (Art. 30)
-- ----------------------------------------------------------------------------

CREATE TABLE personuppgiftsbehandlingar (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    behandling_id VARCHAR(20) UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    verksamhetsomrade VARCHAR(255),
    controller_organization_id VARCHAR(255),
    dpo_id VARCHAR(255),
    processing_responsible_id VARCHAR(255),
    purpose_description TEXT NOT NULL,
    detailed_purposes JSON,
    legal_basis VARCHAR(50),
    legal_reference VARCHAR(500),
    sensitive_data_basis VARCHAR(50),
    data_subject_categories JSON,
    estimated_data_subject_count INT,
    standard_data_categories JSON,
    processes_sensitive_data TINYINT(1) NOT NULL DEFAULT 0,
    sensitive_data_categories JSON,
    processes_criminal_data TINYINT(1) NOT NULL DEFAULT 0,
    processes_ssn TINYINT(1) NOT NULL DEFAULT 0,
    internal_recipients TEXT,
    external_recipients TEXT,
    data_processors TEXT,
    transfers_to_third_country TINYINT(1) NOT NULL DEFAULT 0,
    third_country_destinations JSON,
    transfer_protection_mechanism VARCHAR(50),
    retention_period VARCHAR(255),
    retention_basis TEXT,
    has_archiving_obligation TINYINT(1) NOT NULL DEFAULT 0,
    technical_measures TEXT,
    organizational_measures TEXT,
    information_method TEXT,
    privacy_policy_url VARCHAR(500),
    dpia_required TINYINT(1),
    dpia_date DATE,
    dpia_results TEXT,
    data_source TEXT,
    has_automated_decisions TINYINT(1) NOT NULL DEFAULT 0,
    automated_decision_logic TEXT,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_pub_controller_org FOREIGN KEY (controller_organization_id) REFERENCES organizations(id),
    CONSTRAINT fk_pub_dpo FOREIGN KEY (dpo_id) REFERENCES persons(id),
    CONSTRAINT fk_pub_responsible FOREIGN KEY (processing_responsible_id) REFERENCES persons(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE personuppgiftsbehandling_system_links (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    behandling_id VARCHAR(255) NOT NULL,
    system_id VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_psl_behandling FOREIGN KEY (behandling_id) REFERENCES personuppgiftsbehandlingar(id) ON DELETE CASCADE,
    CONSTRAINT fk_psl_system FOREIGN KEY (system_id) REFERENCES systems(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- AI Act
-- ----------------------------------------------------------------------------

CREATE TABLE ai_applications (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    ai_application_id VARCHAR(20) UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    risk_category VARCHAR(50) NOT NULL,
    high_risk_area VARCHAR(100),
    classification_justification TEXT,
    classification_date DATE,
    classification_responsible_id VARCHAR(255),
    system_id VARCHAR(255) NOT NULL,
    owner_organization_id VARCHAR(255),
    contact_person_id VARCHAR(255),
    oversight_responsible_id VARCHAR(255),
    incident_contact_id VARCHAR(255),
    usage_description TEXT,
    fria_required TINYINT(1),
    fria_date DATE,
    fria_results TEXT,
    transparency_type VARCHAR(50),
    transparency_measures TEXT,
    monitoring_measures TEXT,
    registration_status VARCHAR(50),
    registration_date DATE,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ai_system FOREIGN KEY (system_id) REFERENCES systems(id) ON DELETE CASCADE,
    CONSTRAINT fk_ai_owner_org FOREIGN KEY (owner_organization_id) REFERENCES organizations(id),
    CONSTRAINT fk_ai_contact FOREIGN KEY (contact_person_id) REFERENCES persons(id),
    CONSTRAINT fk_ai_oversight FOREIGN KEY (oversight_responsible_id) REFERENCES persons(id),
    CONSTRAINT fk_ai_incident FOREIGN KEY (incident_contact_id) REFERENCES persons(id),
    CONSTRAINT fk_ai_classification FOREIGN KEY (classification_responsible_id) REFERENCES persons(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- Information Management (IH-planer)
-- ----------------------------------------------------------------------------

CREATE TABLE foreskrifter (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    beteckning VARCHAR(50) NOT NULL UNIQUE,
    namn VARCHAR(255) NOT NULL,
    beskrivning TEXT,
    utfardare VARCHAR(50) NOT NULL,
    giltig_fran DATE,
    giltig_tom DATE,
    url VARCHAR(500),
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE informationshanteringsplaner (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    namn VARCHAR(255) NOT NULL,
    version VARCHAR(50),
    beskrivning TEXT,
    organisation_id VARCHAR(255) NOT NULL,
    giltig_fran DATE,
    giltig_tom DATE,
    beslutsdatum DATE,
    beslutsparagraf VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ihp_organisation FOREIGN KEY (organisation_id) REFERENCES organizations(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE handlingstyper (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    ih_plan_id VARCHAR(255) NOT NULL,
    klassa_process_id INT,
    namn VARCHAR(255) NOT NULL,
    beskrivning TEXT,
    format_id INT,
    bevarande_gallring_id INT,
    gallringsfrist_ar INT,
    gallringsfrist_typ_id INT,
    gallringsforeskrift_id VARCHAR(255),
    bevarande_lagstod_id INT,
    registrering_id INT,
    it_system_id VARCHAR(255),
    forvaring VARCHAR(255),
    leverans_till_arkiv VARCHAR(255),
    leveransfrist_ar INT,
    sekretess TINYINT(1) NOT NULL DEFAULT 0,
    sekretess_lagrum_id INT,
    innehaller_personuppgifter TINYINT(1) NOT NULL DEFAULT 0,
    personuppgift_id INT,
    anmarkning TEXT,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ht_ihplan FOREIGN KEY (ih_plan_id) REFERENCES informationshanteringsplaner(id) ON DELETE CASCADE,
    CONSTRAINT fk_ht_klassa_process FOREIGN KEY (klassa_process_id) REFERENCES klassa_processer(id),
    CONSTRAINT fk_ht_format FOREIGN KEY (format_id) REFERENCES kod_format(id),
    CONSTRAINT fk_ht_bev_gall FOREIGN KEY (bevarande_gallring_id) REFERENCES kod_bevarande_gallring(id),
    CONSTRAINT fk_ht_gall_typ FOREIGN KEY (gallringsfrist_typ_id) REFERENCES kod_gallringsfrist_typ(id),
    CONSTRAINT fk_ht_foreskrift FOREIGN KEY (gallringsforeskrift_id) REFERENCES foreskrifter(id),
    CONSTRAINT fk_ht_bev_lagstod FOREIGN KEY (bevarande_lagstod_id) REFERENCES kod_bevarande_lagstod(id),
    CONSTRAINT fk_ht_registrering FOREIGN KEY (registrering_id) REFERENCES kod_registrering(id),
    CONSTRAINT fk_ht_system FOREIGN KEY (it_system_id) REFERENCES systems(id),
    CONSTRAINT fk_ht_sekretess FOREIGN KEY (sekretess_lagrum_id) REFERENCES kod_sekretess_lagrum(id),
    CONSTRAINT fk_ht_personuppgift FOREIGN KEY (personuppgift_id) REFERENCES kod_personuppgift(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE informationsklassningar (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    handlingstyp_id VARCHAR(255) NOT NULL UNIQUE,
    konfidentialitet INT,
    riktighet INT,
    tillganglighet INT,
    sparbarhet INT,
    klassningsdatum DATE,
    klassad_av VARCHAR(255),
    motivering TEXT,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ik_handlingstyp FOREIGN KEY (handlingstyp_id) REFERENCES handlingstyper(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE handlingstyp_ansvariga (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    handlingstyp_id VARCHAR(255) NOT NULL,
    organisation_id VARCHAR(255) NOT NULL,
    roll VARCHAR(255),
    ansvarstyp_id INT,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_hta_handlingstyp FOREIGN KEY (handlingstyp_id) REFERENCES handlingstyper(id) ON DELETE CASCADE,
    CONSTRAINT fk_hta_organisation FOREIGN KEY (organisation_id) REFERENCES organizations(id) ON DELETE CASCADE,
    CONSTRAINT fk_hta_ansvarstyp FOREIGN KEY (ansvarstyp_id) REFERENCES kod_ansvarstyp(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Legacy process table
CREATE TABLE processer (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    processkod VARCHAR(50) UNIQUE,
    namn VARCHAR(255) NOT NULL,
    beskrivning TEXT,
    verksamhetstyp VARCHAR(100),
    processgrupp_id VARCHAR(255),
    aktiv TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_processer_parent FOREIGN KEY (processgrupp_id) REFERENCES processer(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- Audit & observability
-- ----------------------------------------------------------------------------

CREATE TABLE event_logs (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    entity_type VARCHAR(50) NOT NULL,
    entity_id VARCHAR(255) NOT NULL,
    event_type VARCHAR(50) NOT NULL,
    changes JSON,
    performed_by VARCHAR(255) NOT NULL,
    performed_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    description TEXT,
    acknowledged_at TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_event_logs_entity ON event_logs(entity_type, entity_id);

CREATE TABLE security_level_definitions (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    dimension VARCHAR(1) NOT NULL CHECK (dimension IN ('K', 'R', 'T')),
    level INT NOT NULL CHECK (level BETWEEN 0 AND 4),
    label VARCHAR(100) NOT NULL,
    description TEXT,
    consequence TEXT,
    color VARCHAR(7),
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT uq_dimension_level UNIQUE (dimension, level)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------------------------
-- Vulnerability scanning
-- ----------------------------------------------------------------------------

CREATE TABLE vulnerability_scans (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    started_at TIMESTAMP(6),
    completed_at TIMESTAMP(6),
    components_scanned INT NOT NULL DEFAULT 0,
    vulnerabilities_found INT NOT NULL DEFAULT 0,
    error_message TEXT,
    triggered_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_vuln_scans_completed ON vulnerability_scans(completed_at);

CREATE TABLE vulnerability_findings (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    scan_id VARCHAR(255) NOT NULL,
    component_name VARCHAR(255) NOT NULL,
    component_version VARCHAR(100) NOT NULL,
    component_purl VARCHAR(500),
    ecosystem VARCHAR(50) NOT NULL,
    vuln_id VARCHAR(100) NOT NULL,
    aliases JSON,
    summary TEXT,
    severity VARCHAR(50) NOT NULL,
    cvss_score FLOAT,
    fixed_version VARCHAR(100),
    `references` JSON,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_vf_scan FOREIGN KEY (scan_id) REFERENCES vulnerability_scans(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_vuln_findings_scan ON vulnerability_findings(scan_id);
CREATE INDEX idx_vuln_findings_vuln_id ON vulnerability_findings(vuln_id);
CREATE INDEX idx_vuln_findings_severity ON vulnerability_findings(severity);
