insert into fludland.posts(id, title, content, user_id, created_at) values (nextval('fludland.posts_id_seq'),'test', 'large content', 321, current_timestamp);

commit;