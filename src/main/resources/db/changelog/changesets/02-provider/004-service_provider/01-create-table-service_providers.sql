-- liquibase formatted sql
-- changeset umeshghimire:03
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS service_providers(
    id                  INT                 NOT NULL AUTO_INCREMENT,
    version             INT                 NOT NULL DEFAULT 0,

    user_id             INT                 NOT NULL,

    profession          VARCHAR(100)        NOT NULL,
    experience_years    INT                 DEFAULT 0,

    average_rating      DECIMAL(3,2)        DEFAULT 0.00,
    approval_status_id  INT             NOT NULL DEFAULT 1,
    is_active           BOOLEAN             NOT NULL DEFAULT TRUE,
    is_deleted          BOOLEAN             NOT NULL DEFAULT FALSE,

    created_by          INT                 NULL,
    updated_by          INT                 NULL,
    deleted_by          INT                 NULL,

    created_at          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMP           NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_provider_user
    FOREIGN KEY(user_id)
    REFERENCES users(id),
        CONSTRAINT fk_provider_approval_status
        FOREIGN KEY (approval_status_id) REFERENCES approval_status(id)
    );