CREATE SEQUENCE technologies_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE technologies (
                 id              BIGINT NOT NULL DEFAULT nextval('technologies_id_seq'::regclass),
                 name            VARCHAR(64) NOT NULL UNIQUE,
                 CONSTRAINT technologies_pkey PRIMARY KEY (id),
                 CONSTRAINT uq_name UNIQUE (name));
