-- liquibase formatted sql
-- changeset umeshghimire:04
-- preconditions onError:CONTINUE onFail:HALT
CREATE TABLE IF NOT EXISTS provider_profiles
(
    id                  INT            NOT NULL AUTO_INCREMENT,
    version             INT            NOT NULL DEFAULT 0,
    provider_id         INT            NOT NULL,
    bio                 VARCHAR(1000)  NULL,
    years_of_experience INT                     DEFAULT 0,
    hourly_rate         DECIMAL(10, 2) NULL,
    city                VARCHAR(50)    NULL,
    district            VARCHAR(50)    NOT NULL,
    profile_image       VARCHAR(255)   NOT NULL,
    is_active           BOOLEAN        NOT NULL DEFAULT TRUE,
    is_deleted          BOOLEAN        NOT NULL DEFAULT FALSE,
    created_by          INT            NULL,
    updated_by          INT            NULL,
    deleted_by          INT            NULL,
    created_at          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMP      NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_profile_provider
        FOREIGN KEY (provider_id)
            REFERENCES service_providers (id)
);