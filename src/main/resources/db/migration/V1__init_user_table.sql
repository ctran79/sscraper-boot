/* Initialize user table */
CREATE TABLE app_user
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(1024) UNIQUE,
    password  VARCHAR(1024),
    email     varchar(1024) UNIQUE,
    active    BOOLEAN,
    full_name VARCHAR(1024)
);

INSERT INTO app_user(username, password, email, active, full_name)
VALUES ('admin', '$2y$12$wXawG1DEWD81OLiypbTxOOS6WCfysei7c0rNBTLUzGNNEZGFtkpYe', 'admin@admin.com', true, 'Admin');

INSERT INTO app_user(username, password, email, active, full_name)
VALUES ('test', '$2y$12$uxoE2CdZWx1qZiFSCvajJulKL0enD8LEBSNSJUpzdXq9TEk65K59u', 'test@test.com', true, 'Test');

CREATE TABLE user_role
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES app_user(id),
    role VARCHAR(1024) NOT NULL
);

INSERT INTO user_role(user_id, role) SELECT id, 'ROLE_ADMIN' FROM app_user WHERE username='admin';
INSERT INTO user_role(user_id, role) SELECT id, 'ROLE_USER' FROM app_user WHERE username='test';
