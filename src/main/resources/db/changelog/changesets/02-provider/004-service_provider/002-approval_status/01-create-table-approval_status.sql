-- liquibase formatted sql
-- changeset umeshghimire:03
-- preconditions onError:CONTINUE onFail:HALT

-- First, create the approval statuses reference table
CREATE TABLE IF NOT EXISTS approval_status
(
    id          INT          NOT NULL AUTO_INCREMENT,
    version     INT          NOT NULL DEFAULT 0,
    name        VARCHAR(50)  NOT NULL,
    code        VARCHAR(50)  NOT NULL UNIQUE,
    description VARCHAR(255) NULL,
    is_default  BOOLEAN      NOT NULL DEFAULT FALSE,
    is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
    is_deleted  BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_approval_status_code (code)
);
