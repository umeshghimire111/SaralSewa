-- liquibase formatted sql
-- changeset umeshghimire:03
-- preconditions onError:CONTINUE onFail:HALT
ALTER TABLE service_providers
    ADD COLUMN approval_status_id INT NOT NULL DEFAULT 1;

-- liquibase formatted sql
-- changeset umeshghimire:04
-- preconditions onError:CONTINUE onFail:HALT

ALTER TABLE service_providers
    ADD CONSTRAINT fk_provider_approval_status
        FOREIGN KEY (approval_status_id)
            REFERENCES approval_status(id);

-- liquibase formatted sql
-- changeset umeshghimire:05
-- preconditions onError:CONTINUE onFail:HALT

CREATE INDEX idx_service_providers_approval_status
    ON service_providers(approval_status_id);

-- liquibase formatted sql
-- changeset umeshghimire:03
-- preconditions onError:CONTINUE onFail:HALT
DESC service_providers;

-- liquibase formatted sql
-- changeset umeshghimire:05
-- preconditions onError:CONTINUE onFail:HALT
ALTER TABLE service_providers
    ADD CONSTRAINT fk_provider_approval_status
        FOREIGN KEY (approval_status_id)
            REFERENCES approval_status(id);

