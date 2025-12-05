CREATE TABLE barbershops (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(50) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255),
    plan VARCHAR(50) DEFAULT 'START',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT,
    CONSTRAINT uq_barbershop_document UNIQUE (tenant_id, document)
);

CREATE INDEX idx_barbershop_tenant ON barbershops (tenant_id);

CREATE TABLE branches (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(50),
    barbershop_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT,
    CONSTRAINT fk_branch_barbershop FOREIGN KEY (barbershop_id) REFERENCES barbershops (id)
);

CREATE INDEX idx_branch_tenant ON branches (tenant_id);
CREATE INDEX idx_branch_barbershop ON branches (barbershop_id);

CREATE TABLE barbers (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(50),
    commission_rate NUMERIC(8,2),
    skills TEXT,
    active BOOLEAN DEFAULT TRUE,
    branch_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT,
    CONSTRAINT fk_barber_branch FOREIGN KEY (branch_id) REFERENCES branches (id)
);

CREATE INDEX idx_barber_tenant ON barbers (tenant_id);
CREATE INDEX idx_barber_branch ON barbers (branch_id);

CREATE TABLE clients (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(50),
    loyalty_points INT DEFAULT 0,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT
);

CREATE INDEX idx_client_tenant ON clients (tenant_id);

CREATE TABLE services (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    duration_minutes INT,
    price NUMERIC(10,2) NOT NULL,
    discount_percent INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT
);

CREATE INDEX idx_service_tenant ON services (tenant_id);

CREATE TABLE appointments (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    branch_id UUID,
    barber_id UUID,
    client_id UUID,
    service_id UUID,
    start TIMESTAMP,
    duration_minutes INT,
    status VARCHAR(32) NOT NULL,
    price NUMERIC(10,2),
    notes TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT,
    CONSTRAINT fk_appointment_branch FOREIGN KEY (branch_id) REFERENCES branches (id),
    CONSTRAINT fk_appointment_barber FOREIGN KEY (barber_id) REFERENCES barbers (id),
    CONSTRAINT fk_appointment_client FOREIGN KEY (client_id) REFERENCES clients (id),
    CONSTRAINT fk_appointment_service FOREIGN KEY (service_id) REFERENCES services (id)
);

CREATE INDEX idx_appointment_tenant ON appointments (tenant_id);
CREATE INDEX idx_appointment_start ON appointments (start);
CREATE INDEX idx_appointment_status ON appointments (status);
