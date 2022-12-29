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
    date_time     TIMESTAMP,
    status_id     INTEGER,
    comment       VARCHAR(512),
        CONSTRAINT one_to_one_pkey PRIMARY KEY (id)
);

INSERT INTO one_to_one
VALUES (nextval('one_to_one_id_seq'::regclass), 1, 2, 1, '2022-12-12 20:41:03.000000', 3, 'comment'),
       (nextval('one_to_one_id_seq'::regclass), 2, 1, 2, '2022-11-12 20:41:03.000000', 3, 'comment');