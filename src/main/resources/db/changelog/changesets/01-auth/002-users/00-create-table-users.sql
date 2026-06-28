-- liquibase formatted sql
-- changeset umeshghimire:01
-- preconditions onError:CONTINUE onFail: HALT

CREATE TABLE IF NOT EXISTS users(
      id              INT                 NOT NULL       AUTO_INCREMENT,
      version         INT                 NOT NULL       DEFAULT 0,
      description     VARCHAR(255)        NULL,
      first_name      VARCHAR(100)        NOT NULL,
      last_name       VARCHAR(100)        NULL,

      email           VARCHAR(150)        NOT NULL      UNIQUE ,
      password        VARCHAR(255)        NOT NULL,
      phone           VARCHAR(20)         NOT NULL,

      address         VARCHAR(50)         NULL,
      profile_image   VARCHAR(255)        NULL,

      role_id         INT                 NOT NULL,
      status_id                       INT             NOT NULL,

      last_logged_in_time             TIMESTAMP       NULL,
      wrong_password_attempt_count    INT             NOT NULL DEFAULT 0,

      is_active       BOOLEAN             NOT NULL DEFAULT TRUE,
      is_deleted      BOOLEAN             NOT NULL DEFAULT FALSE,

      created_by       INT                NULL,
      updated_by       INT                NULL,
      deleted_by       INT                NULL,

      created_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_at      TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      deleted_at      TIMESTAMP           NULL,

      PRIMARY KEY (id),
      UNIQUE KEY users_email (email),

      CONSTRAINT users_roles
      FOREIGN KEY (role_id) REFERENCES roles(id),
      CONSTRAINT fk_users_status
          FOREIGN KEY (status_id) REFERENCES status(id)


);