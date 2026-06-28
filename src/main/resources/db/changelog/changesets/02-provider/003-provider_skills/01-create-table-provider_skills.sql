-- liquibase formatted sql
-- changeset umeshghimire:04
-- preconditions onError:CONTINUE onFail:HALT


CREATE TABLE IF NOT EXISTS provider_skills(
    id              INT                 NOT NULL AUTO_INCREMENT,
    version         INT                 NOT NULL DEFAULT 0,

     provider_id     INT                 NOT NULL,

    skill_name      VARCHAR(100)        NOT NULL,

    description     VARCHAR(255)        NULL,

    is_active         BOOLEAN         NOT NULL DEFAULT TRUE,
    is_deleted        BOOLEAN         NOT NULL DEFAULT FALSE,

    created_by        INT             NULL,
    updated_by        INT             NULL,
    deleted_by        INT             NULL,

    created_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at        TIMESTAMP       NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_provider_skills_provider
    FOREIGN KEY(provider_id)
    REFERENCES service_providers(id)
    );