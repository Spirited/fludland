create table user_group (
    id bigserial not null,
    name varchar(100),
    description varchar(150),

    CONSTRAINT pk_user_group primary key (id)
);