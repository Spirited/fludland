create table token (
    id UUID NOT NULL,
    token varchar(255) NOT NULL,
    refresh_token varchar(255),
    created_at timestamp NOT NULL,
    expired_at timestamp NOT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);