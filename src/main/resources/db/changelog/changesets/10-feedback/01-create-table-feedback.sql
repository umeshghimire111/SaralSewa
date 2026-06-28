-- liquibase formatted sql
-- changeset umeshghimire:18
-- preconditions onError:CONTINUE onFail:HALT
CREATE TABLE IF NOT EXISTS feedback
(
    id            INT          NOT NULL AUTO_INCREMENT,
    version       INT          NOT NULL DEFAULT 0,
    user_id       INT          NOT NULL,
    subject       VARCHAR(150) NOT NULL,
    message       VARCHAR(500) NOT NULL,
    feedback_type VARCHAR(50)  NULL,
    is_active     BOOLEAN      NOT NULL DEFAULT TRUE,
    is_deleted    BOOLEAN      NOT NULL DEFAULT FALSE,
    created_by    INT          NULL,
    updated_by    INT          NULL,
    deleted_by    INT          NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMP    NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_feedback_user FOREIGN KEY (user_id) REFERENCES users (id)
);