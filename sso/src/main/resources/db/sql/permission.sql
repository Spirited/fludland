create table permission (
    id bigserial not null,
    name varchar(100),
    description varchar(120),

    CONSTRAINT pk_permission primary key (id)
)