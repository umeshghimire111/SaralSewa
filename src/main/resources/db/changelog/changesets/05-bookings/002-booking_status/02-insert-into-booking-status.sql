-- liquibase formatted sql
-- changeset umeshghimire:13
-- preconditions onError:CONTINUE onFail:HALT

INSERT INTO booking_statuses(name,code,is_default)
VALUES
    ('Pending','PENDING',TRUE),
    ('Accepted','ACCEPTED',FALSE),
    ('In Progress','IN_PROGRESS',FALSE),
    ('Completed','COMPLETED',FALSE),
    ('Cancelled','CANCELLED',FALSE);