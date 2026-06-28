-- liquibase formatted sql
-- changeset umeshghimire:03
-- preconditions onError:CONTINUE onFail:HALT


INSERT INTO approval_status (name, code, is_default)
VALUES ('Pending', 'PENDING', TRUE),
       ('Approved', 'APPROVED', FALSE),
       ('Rejected', 'REJECTED', FALSE),
       ('Suspended', 'SUSPENDED', FALSE);

DESC approval_status;