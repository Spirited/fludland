create table profiles (
    id serial not null ,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(100),
    user_id integer references users(id) not null ,

    CONSTRAINT pk_profiles primary key (id)
);