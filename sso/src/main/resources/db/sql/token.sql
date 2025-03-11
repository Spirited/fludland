create table token (
    id BIGSERIAL NOT NULL,
    token varchar(255) NOT NULL,
    refresh_token varchar(255),
    user_id BIGINT NOT NULL,
    created_at timestamp NOT NULL,
    expired_at timestamp NOT NULL,
    updated_at timestamp,

    CONSTRAINT pk_token PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);