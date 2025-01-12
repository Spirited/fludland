create table userprofile (
    id bigserial not null primary key,
    username varchar(50) not null,
    firstName varchar(25),
    lastName varchar(25),
    email varchar(50),
    phone varchar(15),
    userid BIGINT not null,
    logoId BIGINT
);