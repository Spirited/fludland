create table posts (
    id serial not null,
    title varchar(255) not null,
    content varchar(3000) not null,
    user_id integer not null,

    CONSTRAINT pk_post_id primary key (id)
)