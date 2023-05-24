CREATE SEQUENCE user_roles_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE user_roles (
    id            BIGINT PRIMARY KEY          DEFAULT nextval('user_roles_id_seq'),
    code          VARCHAR(32)                              NOT NULL,
    CONSTRAINT uq_user_roles_code UNIQUE (code)
);

CREATE SEQUENCE user_permissions_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE user_permissions (
    id            BIGINT PRIMARY KEY          DEFAULT nextval('user_permissions_id_seq'),
    code          VARCHAR(64)                              NOT NULL,
    description varchar,
    CONSTRAINT uq_user_permissions_code UNIQUE (code)
);


CREATE TABLE user_roles_permissions (
    user_role_id BIGINT NOT NULL,
    user_permission_id  BIGINT NOT NULL,
    CONSTRAINT user_roles_permissions_pkey PRIMARY KEY (user_role_id, user_permission_id),
    FOREIGN KEY (user_role_id) REFERENCES user_roles (id) match simple on update no action on delete no action,
    FOREIGN KEY (user_permission_id) REFERENCES user_permissions (id) match simple on update no action on delete no action
);

CREATE SEQUENCE users_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE users(
                      id      BIGINT DEFAULT nextval('users_id_seq'::regclass),
                      email VARCHAR(128),
                      telegram_user_name VARCHAR(128),
                      telegram_chat_id BIGINT,
                      telegram_user_id BIGINT NOT NULL,
                      password VARCHAR(256) NOT NULL,
                      name VARCHAR(64),
                      sur_name VARCHAR(64),
                      status VARCHAR(64) NOT NULL,
                      role_id BIGINT NOT NULL REFERENCES user_roles(id),
                      CONSTRAINT users_pkey PRIMARY KEY (id),
                      CONSTRAINT uq_email UNIQUE (email),
                      CONSTRAINT uq_telegram_user_id UNIQUE (telegram_user_id)
);


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