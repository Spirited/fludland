begin transaction;

truncate table users;
truncate table profiles;
truncate table token;

alter sequence sso.users_id_seq restart with 1;
alter sequence sso.profiles_id_seq restart with 1;

commit;