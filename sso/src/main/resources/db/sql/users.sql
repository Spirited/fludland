create table users (
  id serial,
  username  varchar(255),
  password  varchar(255),
  status    varchar(255),

  profile_id integer references profiles(id),

  CONSTRAINT pk_user primary key (id)
);