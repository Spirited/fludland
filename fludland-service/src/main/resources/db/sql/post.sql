create table posts (
    id serial not null,
    title varchar(255) not null,
    content varchar(1000) not null,
    user_id integer not null,
    media_file_id integer,
    created_at timestamp not null default current_timestamp,
    modified_at timestamp,

    CONSTRAINT pk_post_id primary key (id)
)