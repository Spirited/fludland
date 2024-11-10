insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test', 'large content', 321, current_timestamp);
insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test', 'large content', 1, current_timestamp);
insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test', 'large content', 2, current_timestamp);
insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test', 'large content', 3, current_timestamp);
insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test', 'large content', 4, current_timestamp);

insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test777_1', 'large content', 777, current_timestamp);
insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test777_2', 'large content', 777, current_timestamp);
insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test777_3', 'large content', 777, current_timestamp);

commit;