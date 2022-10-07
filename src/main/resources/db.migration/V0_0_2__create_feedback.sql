CREATE SEQUENCE feedback_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE feedback(
    id              BIGINT NOT NULL DEFAULT nextval('feedback_id_seq'::regclass),
    one_to_one_id   BIGINT NOT NULL REFERENCES one_to_one(id),
    author_id       BIGINT NOT NULL REFERENCES users(id),
    recipient_id    BIGINT NOT NULL REFERENCES users(id),
    message         VARCHAR(2048) NOT NULL,
    CONSTRAINT feedback_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE user_answer_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE user_answer(
                         id              BIGINT NOT NULL DEFAULT nextval('user_answer_id_seq'::regclass),
                         feedback_id   BIGINT NOT NULL REFERENCES feedback(id),
                         question_id       BIGINT NOT NULL REFERENCES questions(id),
                         response_level_id INTEGER NOT NULL,
                         CONSTRAINT user_answer_pkey PRIMARY KEY (id)
);