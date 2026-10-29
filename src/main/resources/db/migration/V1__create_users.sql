CREATE TABLE users (
                       id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       email         VARCHAR(255) NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       name          VARCHAR(100) NOT NULL,
                       created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
                       updated_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),

                       CONSTRAINT uq_users_email UNIQUE (email),
                       CONSTRAINT ck_users_email_lowercase CHECK (email = lower(email))
);