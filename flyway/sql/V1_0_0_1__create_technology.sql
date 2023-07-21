CREATE SEQUENCE technologies_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE technologies
(
    id   BIGINT      NOT NULL DEFAULT nextval('technologies_id_seq'::regclass),
    name VARCHAR(64) NOT NULL UNIQUE,
    CONSTRAINT technologies_pkey PRIMARY KEY (id),
    CONSTRAINT uq_name UNIQUE (name)
);

INSERT INTO technologies
VALUES (nextval('technologies_id_seq'::regclass), 'JavaScript'),
       (nextval('technologies_id_seq'::regclass), '.NET Core'),
       (nextval('technologies_id_seq'::regclass), 'C'),
       (nextval('technologies_id_seq'::regclass), 'C#'),
       (nextval('technologies_id_seq'::regclass), 'C++'),
       (nextval('technologies_id_seq'::regclass), 'Go'),
       (nextval('technologies_id_seq'::regclass), 'Java'),
       (nextval('technologies_id_seq'::regclass), 'Kotlin'),
       (nextval('technologies_id_seq'::regclass), 'PHP'),
       (nextval('technologies_id_seq'::regclass), 'Python'),
       (nextval('technologies_id_seq'::regclass), 'React'),
       (nextval('technologies_id_seq'::regclass), 'Ruby'),
       (nextval('technologies_id_seq'::regclass), 'Rust'),
       (nextval('technologies_id_seq'::regclass), 'Spring'),
       (nextval('technologies_id_seq'::regclass), 'SQL'),
       (nextval('technologies_id_seq'::regclass), 'Swift'),
       (nextval('technologies_id_seq'::regclass), '.NET'),
       (nextval('technologies_id_seq'::regclass), 'Docker'),
       (nextval('technologies_id_seq'::regclass), 'Git'),
       (nextval('technologies_id_seq'::regclass), 'Gradle'),
       (nextval('technologies_id_seq'::regclass), 'Kubernetes'),
       (nextval('technologies_id_seq'::regclass), 'Maven'),
       (nextval('technologies_id_seq'::regclass), 'NoSQL'),
       (nextval('technologies_id_seq'::regclass), 'AngularJS'),
       (nextval('technologies_id_seq'::regclass), 'SPARK');