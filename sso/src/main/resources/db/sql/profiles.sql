create table profiles (
    id serial,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(100),
    user_id integer references users(id),

    primary key (id)
);