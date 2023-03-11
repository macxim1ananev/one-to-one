CREATE SEQUENCE one_to_one_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE one_to_one
(
    id            BIGINT  NOT NULL DEFAULT nextval('one_to_one_id_seq'::regclass),
    initiator_id  BIGINT  NOT NULL REFERENCES users (id),
    opponent_id   BIGINT,
    technology_id INTEGER NOT NULL REFERENCES technologies (id),
    level_id      INTEGER NOT NULL,
    date_time     TIMESTAMP,
    status_id     INTEGER,
    initiator_feedback_status_id INTEGER,
    opponent_feedback_status_id INTEGER,
    comment       VARCHAR(512),
        CONSTRAINT one_to_one_pkey PRIMARY KEY (id)
);
