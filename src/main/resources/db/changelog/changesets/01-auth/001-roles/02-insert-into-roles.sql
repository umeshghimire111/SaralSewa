-- liquibase formatted
-- changeset umeshghimire:02
-- preconditions onFail:CONTINUE ONERROR:HALT


INSERT INTO roles (name, code, description, is_active)
VALUES ('Admin', 'ADMIN', 'System Administrator', TRUE),
       ('Customer', 'CUSTOMER', 'Service Seeker', TRUE),
       ('Provider', 'PROVIDER', 'Service Provider', TRUE);