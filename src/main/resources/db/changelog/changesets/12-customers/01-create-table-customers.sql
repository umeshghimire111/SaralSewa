-- liquibase formatted sql
-- changeset umeshghimire:12
-- preconditions onError:CONTINUE onFail:HALT

CREATE TABLE IF NOT EXISTS customer_profiles(
    id                      INT             NOT NULL AUTO_INCREMENT,
    version                 INT             NOT NULL DEFAULT 0,


    user_id                 INT             NOT NULL UNIQUE,


    address                 VARCHAR(255)    NULL,
    saved_addresses         TEXT            NULL,
    preferred_payment       VARCHAR(50)     NULL,
    preferred_contact       VARCHAR(50)     NULL,
    notification_preferences TEXT           NULL,


    is_active               BOOLEAN         NOT NULL DEFAULT TRUE,
    is_deleted              BOOLEAN         NOT NULL DEFAULT FALSE,

    created_by              INT             NULL,
    updated_by              INT             NULL,
    deleted_by              INT             NULL,

    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at              TIMESTAMP       NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_customer_profile_user FOREIGN KEY (user_id) REFERENCES users(id)
);