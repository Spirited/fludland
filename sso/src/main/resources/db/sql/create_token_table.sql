create table token (
    id UUID NOT NULL,
    token varchar(255) NOT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);