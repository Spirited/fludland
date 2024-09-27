begin transaction;

truncate table fludland.posts CASCADE ;
truncate table fludland.thumbs CASCADE ;

alter sequence fludland.posts_id_seq restart with 1;
alter sequence fludland.thumbs_id_seq restart with 1;

commit;