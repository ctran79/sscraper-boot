CREATE TABLE blocked_site
(
    id            SERIAL PRIMARY KEY,
    site          VARCHAR(1024) NOT NULL UNIQUE
);