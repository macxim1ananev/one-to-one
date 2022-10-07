CREATE SEQUENCE questions_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE questions(
                         id             BIGINT NOT NULL DEFAULT nextval('questions_id_seq'::regclass),
                         user_id        BIGINT NOT NULL,
                         question       VARCHAR(2048) NOT NULL,
                         answer         VARCHAR(2048) NOT NULL,
                         CONSTRAINT questions_pkey PRIMARY KEY (id)
);