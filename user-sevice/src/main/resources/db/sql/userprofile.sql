create table userprofile (
    id bigserial not null,
    username varchar(50) not null,
    firstName varchar(25),
    lastName varchar(25),
    email varchar(50),
    phone varchar(15),
    logoId integer
);