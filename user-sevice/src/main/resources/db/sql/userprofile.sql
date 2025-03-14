create table userprofile (
    id bigserial not null unique primary key,
    firstName varchar(25),
    lastName varchar(25),
    birthday date,
    gender varchar(6),
    email varchar(50),
    phone varchar(15),
    user_id BIGINT not null unique,
    logoId BIGINT
);