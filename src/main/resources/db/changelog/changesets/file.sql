-- liquibase formatted sql
-- changeset umeshghimire:drop_all_tables
-- preconditions onError:CONTINUE onFail:HALT

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS booking_status_history;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS availability_slots;
DROP TABLE IF EXISTS provider_services;
DROP TABLE IF EXISTS provider_documents;
DROP TABLE IF EXISTS provider_skills;
DROP TABLE IF EXISTS provider_profiles;
DROP TABLE IF EXISTS service_providers;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS service_categories;
DROP TABLE IF EXISTS user_tokens;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS approval_status;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS roles;
SET FOREIGN_KEY_CHECKS = 1;
SHOW TABLES;

DROP TABLE IF EXISTS notification;
