CREATE TABLE comments (
    id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    content TEXT,
    post_id BIGINT NOT NULL ,
    parent_id BIGINT,
    author_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_comment_post FOREIGN KEY (post_id)
        REFERENCES posts(id) ON DELETE CASCADE,

    CONSTRAINT fk_comment_parent FOREIGN KEY (parent_id)
        REFERENCES comments(id) ON DELETE CASCADE
);