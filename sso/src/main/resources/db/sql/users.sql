create table users (
  id serial,
  username  varchar(255),
  password  varchar(255),
  status    varchar(255),
  created_at timestamp,
  last_login timestamp,

  CONSTRAINT pk_user primary key (id)
);