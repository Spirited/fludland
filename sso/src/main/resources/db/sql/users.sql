create table users (
  id serial,
  username  varchar(255),
  password  varchar(255),
  status    varchar(255),

  CONSTRAINT pk_user primary key (id)
);