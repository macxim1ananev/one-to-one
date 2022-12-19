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
                         comment VARCHAR(1024),
                         CONSTRAINT user_answer_pkey PRIMARY KEY (id)
);


INSERT INTO questions
VALUES (nextval('questions_id_seq'::regclass), 1, 'что такое spring', 'this is crazy framework'),
       (nextval('questions_id_seq'::regclass), 2, 'что такое java', 'this is crazy language');

INSERT INTO feedback
VALUES (nextval('feedback_id_seq'::regclass), 1, 1, 2, 'feedback message long'),
       (nextval('feedback_id_seq'::regclass), 2, 2, 1, 'very good answer');

INSERT INTO user_answer
VALUES (nextval('user_answer_id_seq'::regclass), 1, 1, 3, 'comment for user answer'),
       (nextval('user_answer_id_seq'::regclass), 2, 2, 3, 'user answer was be very good');