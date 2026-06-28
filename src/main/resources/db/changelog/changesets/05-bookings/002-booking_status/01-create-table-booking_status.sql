-- liquibase formatted sql
-- changeset umeshghimire:13
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS booking_statuses(
    id              INT                 NOT NULL AUTO_INCREMENT,
    version         INT                 NOT NULL DEFAULT 0,

    name            VARCHAR(100)        NOT NULL,
    code            VARCHAR(50)         NOT NULL,
    description     VARCHAR(255)        NULL,

    is_default      BOOLEAN             NOT NULL DEFAULT FALSE,

    is_active       BOOLEAN             NOT NULL DEFAULT TRUE,
    is_deleted      BOOLEAN             NOT NULL DEFAULT FALSE,

    created_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY(id),

    UNIQUE KEY uk_booking_status_code(code)
    );