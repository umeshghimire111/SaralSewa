-- liquibase formatted sql
-- changeset umeshghimire:15
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS status
(
    id              INT                 NOT NULL AUTO_INCREMENT,
    version         INT                 NOT NULL DEFAULT 0,

    name            VARCHAR(50)         NOT NULL,
    description     VARCHAR(255)        NULL,
    code            VARCHAR(50)         NOT NULL,

    is_active       BOOLEAN             NOT NULL DEFAULT TRUE,
    is_deleted      BOOLEAN             NOT NULL DEFAULT FALSE,

    created_by      INT                 NULL,
    updated_by      INT                 NULL,
    deleted_by      INT                 NULL,

    created_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at      TIMESTAMP           NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_statuses_code (code),
    UNIQUE KEY uk_statuses_name (name)
);