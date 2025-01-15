
insert into sso.users(id, username, password) values ( nextval('sso.users_id_seq'), 'testuser', '1234ABV');
insert into sso.users(id, username, password) values ( nextval('sso.users_id_seq'), 'super-user', 'qwerty1234');

commit;