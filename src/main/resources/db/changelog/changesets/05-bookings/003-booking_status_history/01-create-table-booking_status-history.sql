-- liquibase formatted sql
-- changeset umeshghimire:15
-- preconditions onError:CONTINUE onFail:HALT
CREATE TABLE IF NOT EXISTS booking_status_history
(
    id            INT          NOT NULL AUTO_INCREMENT,
    version       INT          NOT NULL DEFAULT 0,
    booking_id    INT          NOT NULL,
    old_status_id INT          NULL,
    new_status_id INT          NOT NULL,
    remarks       VARCHAR(255) NULL,
    changed_by    INT          NULL,
    is_deleted    BOOLEAN      NOT NULL DEFAULT FALSE,
    created_by    INT          NULL,
    updated_by    INT          NULL,
    deleted_by    INT          NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMP    NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_history_booking FOREIGN KEY (booking_id) REFERENCES bookings (id),
    CONSTRAINT fk_history_old_status FOREIGN KEY (old_status_id) REFERENCES status (id),
    CONSTRAINT fk_history_new_status FOREIGN KEY (new_status_id) REFERENCES status (id)
);