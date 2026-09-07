-- liquibase formatted sql
-- changeset umeshghimire:13
-- preconditions onError:CONTINUE onFail:HALT
CREATE TABLE otp_codes
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(150) NOT NULL,
    otp_code    VARCHAR(6)   NOT NULL,
    is_verified BOOLEAN      NOT NULL DEFAULT FALSE,
    attempts    INT          NOT NULL DEFAULT 0,
    expires_at  DATETIME     NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL,
    INDEX       idx_otp_email (email)
);