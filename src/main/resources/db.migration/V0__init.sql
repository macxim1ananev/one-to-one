CREATE SEQUENCE users_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE users(
                      id      BIGINT NOT NULL DEFAULT nextval('users_id_seq'::regclass),
                      login VARCHAR(128),
                      email VARCHAR(128),
                      CONSTRAINT users_pkey PRIMARY KEY (id),
                      CONSTRAINT uq_login UNIQUE (login),
                      CONSTRAINT uq_email UNIQUE (email)
);