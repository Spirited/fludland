create table thumbs (
    id bigserial not null,
    post_id bigserial not null,
    user_id serial unique not null,
    version integer,

    constraint pk_thumb primary key(id)
)