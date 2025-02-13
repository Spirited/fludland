CREATE TABLE friends (
    id BIGSERIAL NOT NULL UNIQUE,
    userid BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status VARCHAR(20),
    added_on TIMESTAMP DEFAULT NOW(),
    updated_on TIMESTAMP DEFAULT NOW()
)