create table thumbs (
    id bigserial unique not null,
    post_id bigserial not null,
    user_id serial not null,

    constraint pk_thumb primary key(id)
)