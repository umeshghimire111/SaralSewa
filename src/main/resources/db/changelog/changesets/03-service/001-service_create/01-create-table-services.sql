-- liquibase formatted sql
-- changeset umeshghimire:06
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS services
(
    id                 INT            NOT NULL AUTO_INCREMENT,
    version            INT            NOT NULL DEFAULT 0,
    category_id        INT            NOT NULL,
    name               VARCHAR(150)   NOT NULL,
    code               VARCHAR(50)    NOT NULL,
    description        VARCHAR(255)   NULL,
    estimated_duration INT            NULL,
    base_price         DECIMAL(10, 2) NULL,
    is_active          BOOLEAN        NOT NULL DEFAULT TRUE,
    is_deleted         BOOLEAN        NOT NULL DEFAULT FALSE,
    created_by         INT            NULL,
    updated_by         INT            NULL,
    deleted_by         INT            NULL,
    created_at         TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at         TIMESTAMP      NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_services_code (code),
    CONSTRAINT fk_services_category
        FOREIGN KEY (category_id) REFERENCES service_categories (id)
);