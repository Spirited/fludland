create table thumbs (
    id serial not null,
    post_id serial not null,
    user_id serial not null,

    constraint pk_thumb primary key(id)
)