CREATE SEQUENCE users_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE users(
                      id      BIGINT DEFAULT nextval('users_id_seq'::regclass),
                      email VARCHAR(128),
                      password VARCHAR(256) NOT NULL,
                      name VARCHAR(64) NOT NULL,
                      sur_name VARCHAR(64) NOT NULL,
                      status VARCHAR(64) NOT NULL,
                      CONSTRAINT users_pkey PRIMARY KEY (id),
                      CONSTRAINT uq_email UNIQUE (email)
);

INSERT INTO users
VALUES (nextval('users_id_seq'::regclass), 'maxim@mail.ru', 'maxim-password', 'Maxim', 'Ananev', 'ACTIVATED'),
       (nextval('users_id_seq'::regclass), 'petr@mail.ru', 'petr-password', 'Petr', 'Petrov', 'ACTIVATED');


CREATE SEQUENCE verification_token_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;


CREATE TABLE verification_token(
    id          BIGINT DEFAULT nextval('verification_token_id_seq'::regclass),
    token       VARCHAR(521) NOT NULL,
    user_id     BIGINT NOT NULL REFERENCES users(id),
    expiry_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT verification_token_pkey PRIMARY KEY (id)
);