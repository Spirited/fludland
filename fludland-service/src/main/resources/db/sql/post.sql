CREATE TABLE posts (
    id BIGSERIAL NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    category VARCHAR(255),
    user_id BIGINT NOT NULL,
    media_file_id BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_post_id primary key (id)
)