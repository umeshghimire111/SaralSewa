-- liquibase formatted sql
-- changeset umeshghimire:16
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS reviews(
    id                  INT                 NOT NULL AUTO_INCREMENT,
    version             INT                 NOT NULL DEFAULT 0,

    booking_id          INT                 NOT NULL,

    customer_id         INT                 NOT NULL,
    provider_id         INT                 NOT NULL,

    rating              INT                 NOT NULL,

    review_title        VARCHAR(150)        NULL,
    review_message      VARCHAR(500)        NULL,

    is_active           BOOLEAN             NOT NULL DEFAULT TRUE,
    is_deleted          BOOLEAN             NOT NULL DEFAULT FALSE,

    created_by          INT                 NULL,
    updated_by          INT                 NULL,
    deleted_by          INT                 NULL,

    created_at          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMP           NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_reviews_booking
    FOREIGN KEY(booking_id)
    REFERENCES bookings(id),

    CONSTRAINT fk_reviews_customer
    FOREIGN KEY(customer_id)
    REFERENCES users(id),

    CONSTRAINT fk_reviews_provider
    FOREIGN KEY(provider_id)
    REFERENCES service_providers(id)
    );