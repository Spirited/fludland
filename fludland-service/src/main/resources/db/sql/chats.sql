CREATE TABLE chats (
    id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    members BIGINT,
    messages BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);