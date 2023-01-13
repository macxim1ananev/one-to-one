CREATE SEQUENCE user_statistics_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE user_statistics (
                 id          BIGINT NOT NULL DEFAULT nextval('user_statistics_id_seq'::regclass),
                 user_id     BIGINT  NOT NULL REFERENCES users (id),
                 total_one_to_one_count INTEGER,
                 total_question_count INTEGER,
                 total_point INTEGER,
                 CONSTRAINT user_statistics_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE user_technology_statistics_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE user_technology_statistics(
                 id          BIGINT NOT NULL DEFAULT nextval('user_technology_statistics_id_seq'::regclass),
                 user_statistics_id BIGINT NOT NULL REFERENCES user_statistics(id),
                 technology_id BIGINT NOT NULL REFERENCES technologies(id),
                 total_point INTEGER DEFAULT 0,
                 question_count INTEGER DEFAULT 0,
                 CONSTRAINT user_technology_statistics_pkey PRIMARY KEY (id)
);