CREATE TABLE IF NOT EXISTS provider_services
(
    id          INT          NOT NULL AUTO_INCREMENT,
    version     INT          NOT NULL DEFAULT 0,
    provider_id INT          NOT NULL,
    service_id  INT          NOT NULL,
    description VARCHAR(255) NULL,
    is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
    created_by  INT          NULL,
    updated_by  INT          NULL,
    deleted_by  INT          NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMP    NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_provider_services_provider FOREIGN KEY (provider_id) REFERENCES service_providers (id),
    CONSTRAINT fk_provider_services_service FOREIGN KEY (service_id) REFERENCES services (id)
);
