-- liquibase formatted sql
-- changeset umeshghimire:05
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS service_categories(
    id              INT                 NOT NULL  AUTO_INCREMENT,
    version         INT                 NOT NULL DEFAULT 0,

    name            VARCHAR(100)        NOT NULL,
    code            VARCHAR(50)         NOT NULL,
    description     VARCHAR(255)        NULL,

    is_active       BOOLEAN         NOT NULL DEFAULT TRUE,
    is_deleted      BOOLEAN         NOT NULL DEFAULT FALSE,
    created_by      INT             NULL,
    updated_by      INT             NULL,
    deleted_by      INT             NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at      TIMESTAMP       NULL,

    PRIMARY KEY(id),
    UNIQUE KEY uk_service_category_code(code)
    );