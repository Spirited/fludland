begin transaction;

truncate table userservice.userprofile CASCADE ;
alter sequence userservice.userprofile_id_seq restart with 1;

commit;