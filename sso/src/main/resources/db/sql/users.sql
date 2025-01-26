CREATE TABLE users (
  id            BIGSERIAL NOT NULL,
  username      VARCHAR(255) NOT NULL,
  password      VARCHAR(255) NOT NULL,
  status        VARCHAR(255),
  created_at    TIMESTAMP,
  last_login    TIMESTAMP,

  CONSTRAINT pk_user PRIMARY KEY (id)
);