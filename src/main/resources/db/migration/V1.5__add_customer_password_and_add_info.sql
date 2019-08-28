alter table customers add password varchar(250);
alter table agents alter column password type varchar(250);

update customers set password = '1234' where id = 1 or id = 2 or id = 3;
