set search_path to fileservice;

begin transaction;

truncate table images CASCADE ;
alter sequence images_id_seq restart with 1;

commit;