begin transaction;

truncate table sso.profiles CASCADE ;
-- truncate table sso.token;
truncate table sso.users CASCADE ;

alter sequence sso.users_id_seq restart with 1;
alter sequence sso.profiles_id_seq restart with 1;

commit;