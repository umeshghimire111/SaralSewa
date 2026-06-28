-- liquibase formatted sql
-- changeset umeshghimire:04
-- preconditions onError:CONTINUE onFail:HALT


CREATE TABLE IF NOT EXISTS availability_slots(
    id                  INT                 NOT NULL AUTO_INCREMENT,
    version             INT                 NOT NULL DEFAULT 0,
    description         VARCHAR(255)        NULL,
    provider_id         INT                 NOT NULL,

    available_date      DATE                NOT NULL,

    start_time          TIME                NOT NULL,
    end_time            TIME                NOT NULL,

    is_booked           BOOLEAN             NOT NULL DEFAULT FALSE,

    is_active           BOOLEAN             NOT NULL DEFAULT TRUE,
    is_deleted          BOOLEAN             NOT NULL DEFAULT FALSE,

    created_at          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

     PRIMARY KEY(id),

    CONSTRAINT fk_slot_provider
    FOREIGN KEY(provider_id)
    REFERENCES service_providers(id)
    );