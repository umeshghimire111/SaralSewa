-- liquibase formatted sql
-- changeset umeshghimire:15
-- preconditions onError:CONTINUE onFail:HALT


INSERT INTO status (name, code, description, is_active)
VALUES ('Active', 'ACTIVE', 'User is active', TRUE),
       ('Inactive', 'INACTIVE', 'User is inactive', TRUE),
       ('Blocked', 'BLOCKED', 'User is blocked', TRUE);