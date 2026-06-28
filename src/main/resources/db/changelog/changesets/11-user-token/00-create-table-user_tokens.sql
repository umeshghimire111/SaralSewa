-- liquibase formatted sql
-- changeset umeshghimire:06
-- preconditions onError:CONTINUE onFail:HALT
CREATE TABLE IF NOT EXISTS user_tokens
(
    id            INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    version       INT                            NOT NULL,
    access_token  VARCHAR(255)                      NOT NULL,
    refresh_token VARCHAR(255)                      NOT NULL,
    logged_out    BOOLEAN                           NOT NULL DEFAULT FALSE,
    user_id       INT                            NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);