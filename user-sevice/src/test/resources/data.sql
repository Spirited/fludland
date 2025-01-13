set search_path to userservice;

insert into userprofile(firstname, lastname, birthday, gender, email, phone, userid, logoid)
values ('john', 'smith', '12-11-1987', 'MALE', 'john@smith.com', '380971228367', 42, 13);

commit;