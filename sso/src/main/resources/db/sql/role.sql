create table role (
    id bigserial not null,
    name varchar(50),
    description varchar(150),

    CONSTRAINT pk_role primary key (id)
)