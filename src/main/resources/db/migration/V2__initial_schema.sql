CREATE TABLE article
(
    id            SERIAL PRIMARY KEY,
    link          VARCHAR(2048) NOT NULL UNIQUE,
    title         VARCHAR(1024) NOT NULL,
    note          VARCHAR(8192),
    scraping_date DATE          NOT NULL DEFAULT CURRENT_DATE,
    favorite      BOOLEAN       NOT NULL DEFAULT FALSE,
    attachment    VARCHAR(1024),
    visited       BOOLEAN                DEFAULT FALSE,
    deleted       BOOLEAN       NOT NULL DEFAULT FALSE
);

CREATE TABLE topic
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(1024) NOT NULL,
    link   VARCHAR(2048) NOT NULL,
    parser VARCHAR(1024) NOT NULL
);

CREATE TABLE topic_role
(
    id     SERIAL PRIMARY KEY,
    topic_id INTEGER NOT NULL REFERENCES topic(id),
    role VARCHAR(1024) NOT NULL
);

CREATE TABLE article_topic
(
    id SERIAL PRIMARY KEY,
    article_id INTEGER NOT NULL REFERENCES article(id),
    topic_id INTEGER NOT NULL REFERENCES topic(id)
);