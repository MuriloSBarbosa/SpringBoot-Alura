alter table usuario add role varchar(100) not null;

update usuario set role = 'ADMIN' where id >= 1;